package com.scepea.vindicator.ai.steering;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.physics.box2d.PhysicsComponent;
import com.scepea.vindicator.physics.box2d.PhysicsService;

import javax.inject.Inject;

public class SteeringSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private final PhysicsService physicsService;

    public final SteeringAcceleration<Vector2> steeringOutput =
            new SteeringAcceleration<>(new Vector2());

    @Inject
    SteeringSystem(
            PhysicsService physicsService
    ){
        this.physicsService = physicsService;

    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(SteerableComponent.class, SteeringBehaviorComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity entity: entities){
            SteeringBehaviorComponent steeringBehaviorComponent = entity.getComponent(SteeringBehaviorComponent.class);
            PhysicsComponent physicsComponent = entity.getComponent(PhysicsComponent.class);
            if (steeringBehaviorComponent.steeringBehavior != null && steeringBehaviorComponent.steeringBehavior.isEnabled() && physicsComponent != null) {
                steeringBehaviorComponent.steeringBehavior.calculateSteering(steeringOutput);

                physicsService.applyAccelerationToCenter(physicsComponent.getBody(), steeringOutput.linear);
                physicsService.createAngularAccelerationCommand(physicsComponent.getBody(), steeringOutput.angular);

            }
        }
    }

}
