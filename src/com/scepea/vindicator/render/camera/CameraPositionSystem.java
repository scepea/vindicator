package com.scepea.vindicator.render.camera;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scepea.vindicator.physics.box2d.Box2dPhysicsComponent;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

/*
//TODO This could probably be removed by making the camera and entity and deciding how we want to operate with it.
 */
public class CameraPositionSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private final OrthographicCamera orthographicCamera;

    @Inject
    public CameraPositionSystem(OrthographicCamera orthographicCamera){
        this.orthographicCamera = orthographicCamera;
        priority = SystemPriority.ACTIONS.ordinal();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(FocusComponent.class, Box2dPhysicsComponent.class).get());//FIXME Should depend on the transform not the physics body.
    }


    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            orthographicCamera.position.set(entity.getComponent(Box2dPhysicsComponent.class).getWorldCenter(),0);
            orthographicCamera.update();
        }
    }
}
