package com.scepea.vindicator.physics.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.scepea.vindicator.transform.Transform;
import com.scepea.vindicator.transform.component.TransformComponent;

import javax.inject.Inject;

public class PhysicsSystem extends EntitySystem {

    private final World world;
    private ImmutableArray<Entity> entities;

    @Inject
    public PhysicsSystem(World world){
        this.priority = Integer.MIN_VALUE;
        this.world = world;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        world.step(deltaTime, 6, 2);
        for (Entity entity : entities) {
            Body body = entity.getComponent(PhysicsComponent.class).getBody();
            entity.add(new TransformComponent(new Transform(body.getPosition(), body.getAngle())));
        }
    }

}
