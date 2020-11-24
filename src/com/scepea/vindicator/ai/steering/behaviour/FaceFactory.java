package com.scepea.vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;

import javax.inject.Inject;

public class FaceFactory <T extends Vector<T>> {

    @Inject
    public FaceFactory() {}

    public Face<T> create(Steerable<T> owner, Location<T> location){
        if (owner != null){
            Face<T> face = new Face<>(owner, location);
            float typicalAngleDeg = 180f;
            float maxLikelyVelocity = Math.min((float) Math.sqrt(2 * owner.getMaxAngularAcceleration() * (typicalAngleDeg * MathUtils.degreesToRadians)), owner.getMaxAngularSpeed());
            float angularDecelerationRadius = (maxLikelyVelocity * maxLikelyVelocity) / (2 * owner.getMaxAngularAcceleration());

            face.setAlignTolerance(0.01f * MathUtils.degreesToRadians);
            face.setDecelerationRadius(angularDecelerationRadius);
            face.setTimeToTarget(1/60f);
            return face;
        } else {
            throw new IllegalArgumentException("Entity must have a steerable component associated with it.");
        }
    }
}
