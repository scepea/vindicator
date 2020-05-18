package com.scepea.vindicator.physics.collision;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import javax.inject.Inject;


public class CleanupSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Inject
    public CleanupSystem(){
        priority = Integer.MAX_VALUE; // This should always be the last system to run.
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity entity: entities) {
            entity.remove(CollisionComponent.class);
        }
    }
}
