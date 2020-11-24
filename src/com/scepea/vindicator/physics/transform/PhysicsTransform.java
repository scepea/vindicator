package com.scepea.vindicator.physics.transform;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.scepea.vindicator.transform.Transform;

public class PhysicsTransform extends Transform {
    private final Body body;

    public PhysicsTransform(Body body) {
        this.body = body;
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public float getRotation() {
        return body.getAngle();
    }
}
