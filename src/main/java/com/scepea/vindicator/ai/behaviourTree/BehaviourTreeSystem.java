package com.scepea.vindicator.ai.behaviourTree;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import javax.inject.Inject;


public class BehaviourTreeSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Inject
    public BehaviourTreeSystem() {}

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BehaviourTreeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            entity.getComponent(BehaviourTreeComponent.class).getBehaviorTree().step();
        }
    }

}
