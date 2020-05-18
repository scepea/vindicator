package com.scepea.vindicator.render.camera;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scepea.vindicator.physics.box2d.PhysicsComponent;

import javax.inject.Inject;

public class CameraPositionSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private final OrthographicCamera orthographicCamera;

    @Inject
    public CameraPositionSystem(OrthographicCamera orthographicCamera){
        this.orthographicCamera = orthographicCamera;
        priority = Integer.MIN_VALUE + 1;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(FocusComponent.class, PhysicsComponent.class).get());
    }


    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            orthographicCamera.position.set(entity.getComponent(PhysicsComponent.class).getGlobalCenterOfMass(),0);
            orthographicCamera.update();
        }
    }
}
