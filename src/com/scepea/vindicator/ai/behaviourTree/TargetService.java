package com.scepea.vindicator.ai.behaviourTree;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.entity.EntityOwnershipService;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TargetService {

    private final EntityOwnershipService entityOwnershipService;

    @Inject
    public TargetService(EntityOwnershipService entityOwnershipService) {
        this.entityOwnershipService = entityOwnershipService;
    }

    public Optional<Entity> getFirstMatchingTargetInBranch(Entity entity){
        Optional<TargetComponent> targetComponent = entityOwnershipService.getFirstMatchingComponentInBranch(entity, TargetComponent.class);
        if (targetComponent.isPresent()) {
            return Optional.of(targetComponent.get().getEntity());
        } else {
            return Optional.empty();
        }

    }

    public Optional<Entity> getTarget(Entity owner){
        TargetComponent targetComponent = owner.getComponent(TargetComponent.class);
        if (targetComponent != null) {
            return Optional.of(targetComponent.getEntity());
        } else {
            return Optional.empty();
        }
    }

    public void addTarget(Entity owner, Entity target) {
        owner.add(new TargetComponent(target));
        TargetOfComponent targetOfComponent = target.getComponent(TargetOfComponent.class);
        if (targetOfComponent == null){
            targetOfComponent = new TargetOfComponent();
            target.add(targetOfComponent);
        }
        targetOfComponent.getEntities().add(owner);
    }

    public void removeTarget(Entity owner) {
        TargetComponent targetComponent = owner.getComponent(TargetComponent.class);
        if (targetComponent != null) {
            Entity target = targetComponent.getEntity();
            TargetOfComponent targetOfComponent = target.getComponent(TargetOfComponent.class);
            if (targetOfComponent != null) {
                targetOfComponent.getEntities().remove(owner);
            }
            owner.remove(TargetComponent.class);
        }
    }

    public void eraseTarget(Entity target){
        TargetOfComponent targetOfComponent = target.getComponent(TargetOfComponent.class);
        if (targetOfComponent != null) {
            for (Entity entity : targetOfComponent.getEntities()) {
                entity.remove(TargetComponent.class);
            }
            target.remove(TargetOfComponent.class);
        }
    }

    private static class TargetComponent implements Component {
        private final Entity entity;

        public TargetComponent(Entity entity) {
            this.entity = entity;
        }

        public Entity getEntity() {
            return entity;
        }

    }

    private static class TargetOfComponent implements Component {
        private final Set<Entity> entities;

        public TargetOfComponent() {
            this.entities = new HashSet<>();
        }

        public Set<Entity> getEntities() {
            return entities;
        }
    }


}
