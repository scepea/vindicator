package vindicator.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.cleanUp.DependantEntitiesComponent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing and navigating the dependencies between entities.
 */
public class EntityService {

    @Inject public EntityService() {}

    public void addDependant(Entity owner, Entity dependant){
        DependantEntitiesComponent dependantEntitiesComponent = owner.getComponent(DependantEntitiesComponent.class);
        if (dependantEntitiesComponent == null) {
            dependantEntitiesComponent = new DependantEntitiesComponent();
            owner.add(dependantEntitiesComponent);
        }
        dependantEntitiesComponent.addDependantEntity(dependant);
    }

    public <T extends EntityComponent, U extends Component> Optional<U> getFirstMatchingComponentInBranch(Entity entity, Class<T> relationComponentClass, Class<U> targetComponentClass) {
        T relationComponent;
        U targetComponent;
        do {
            targetComponent = entity.getComponent(targetComponentClass);
            relationComponent = entity.getComponent(relationComponentClass);
            if (relationComponent != null) {
                entity = relationComponent.getEntity();
            } else {
                entity = null;
            }
        } while (targetComponent == null && entity != null);
        return Optional.ofNullable(targetComponent);
    }

    public <T extends EntityComponent> List<Entity> getEntityBranch(Entity entity, Class<T> relationComponentClass){
        List<Entity> entityList = new ArrayList<>();
        T relationComponent;
        do {
            entityList.add(entity);
            relationComponent = entity.getComponent(relationComponentClass);
            if (relationComponent != null) {
                entity = relationComponent.getEntity();
            } else {
                entity = null;
            }
        } while ( entity != null);

        return entityList;
    }

    public <T extends EntityComponent, U extends Component> List<U> getAllMatchingComponentsInEntityBranch(Entity entity, Class<T> relationComponentClass, Class<U> targetComponentClass){
        List<U> componentList = new ArrayList<>();

        for (Entity anEntity : getEntityBranch(entity, relationComponentClass)){
            U targetComponent  = anEntity.getComponent(targetComponentClass);
            if (targetComponent != null) {
                componentList.add(targetComponent);
            }
        }

        return componentList;
    }

    public <T extends EntityComponent> Entity getRootEntity(Entity entity, Class<T> relationComponentClass){
        Entity rootEntity;
        T relationComponent;
        do {
            rootEntity = entity;
            relationComponent = entity.getComponent(relationComponentClass);
            if (relationComponent != null) {
                entity = relationComponent.getEntity();
            } else {
                entity = null;
            }
        } while ( entity != null);

        return rootEntity;
    }
}
