package com.scepea.vindicator.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EntityOwnershipService {

    private final EntityService entityService;

    @Inject
    public EntityOwnershipService(EntityService entityService) {
        this.entityService = entityService;
    }

    public void addDependant(Entity owner, Entity dependant){
        DependantEntitiesComponent dependantEntitiesComponent = owner.getComponent(DependantEntitiesComponent.class);
        if (dependantEntitiesComponent == null) {
            dependantEntitiesComponent = new DependantEntitiesComponent();
            owner.add(dependantEntitiesComponent);
        }
        dependantEntitiesComponent.addDependantEntity(dependant);

        dependant.add(new OwnerComponent(owner));
    }

    public Set<Entity> getDependants(Entity entity){
        DependantEntitiesComponent dependantEntitiesComponent = entity.getComponent(DependantEntitiesComponent.class);
        if (dependantEntitiesComponent != null) {
            return dependantEntitiesComponent.getDependantEntities();
        }
        return new HashSet<>();
    }

    public <U extends Component> Optional<U> getFirstMatchingComponentInBranch(Entity entity, Class<U> targetComponentClass) {
        return entityService.getFirstMatchingComponentInBranch(entity, OwnerComponent.class, targetComponentClass);
    }

    public <U extends Component> Optional<U> getNextMatchingComponentInBranch(Entity entity, Class<U> targetComponentClass) {
        return entityService.getNextMatchingComponentInBranch(entity, OwnerComponent.class, targetComponentClass);
    }

    private static class DependantEntitiesComponent implements Component {

        private final Set<Entity> dependantEntities;

        public DependantEntitiesComponent() {
            this.dependantEntities = new HashSet<>();
        }

        public Set<Entity> getDependantEntities() {
            return dependantEntities;
        }

        public void addDependantEntity(Entity entity){
            dependantEntities.add(entity);
        }

    }

    /**
     * Generic ownership component. Extremely useful for building composite entities - entities made up from one or many sub entities which existing holistically.
     * Should the root entity no longer exist it should be assumed that all of the sub entities should be recursively removed.
     * More specific ownership relationships may be provided but it is assumed that many relationships will navigate the same ownership tree.
     */
    private static class OwnerComponent extends EntityComponent {

        public OwnerComponent(Entity entity) {
            super(entity);
        }

    }
}
