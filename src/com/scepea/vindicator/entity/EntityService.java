package com.scepea.vindicator.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service for managing and navigating the dependencies between entities.
 */
public class EntityService {

    @Inject public EntityService() {}

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

    public <T extends EntityComponent, U extends Component> Optional<U> getNextMatchingComponentInBranch(Entity entity, Class<T> relationComponentClass, Class<U> targetComponentClass) {
        T relationComponent;
        relationComponent = entity.getComponent(relationComponentClass);
        if (relationComponent != null) {
            entity = relationComponent.getEntity();
        } else {
            return Optional.empty();
        }
        return getFirstMatchingComponentInBranch(entity, relationComponentClass, targetComponentClass);
    }

    public <T extends EntityComponent, U extends Component> Optional<Entity> getFirstMatchingEntityWithComponentInBranch(Entity entity, Class<T> relationComponentClass, Class<U> targetComponentClass) {
        T relationComponent;
        U targetComponent;
        do {
            targetComponent = entity.getComponent(targetComponentClass);
            relationComponent = entity.getComponent(relationComponentClass);
            if (relationComponent != null) {
                entity = relationComponent.getEntity();
            } else {
                break;
            }
        } while (targetComponent == null && entity != null);
        return Optional.ofNullable(entity);
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
