package com.scepea.vindicator.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import javax.inject.Inject;

public class PhysicsService {

    @Inject PhysicsService(){}

    public void applyAccelerationToCenter(Body body, Vector2 acceleration){
        body.applyForceToCenter(acceleration.scl(body.getMass()),true);
    }

    public void createAngularAccelerationCommand(Body body, float angularAcceleration) {
        body.applyTorque(angularAcceleration *
                        (body.getInertia() - (body.getMass() * body.getLocalCenter().dot(body.getLocalCenter()))) //body.getInertia() returns the moment of inertia around the origin, but the moment of inertia around the center of gravity is required. Converted using parallel axis theorem.
                , true);
    }
}
