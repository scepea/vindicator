package com.scepea.vindicator.ai.steering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.physics.box2d.PhysicsComponent;

public class SteerableComponent implements Component, Steerable<Vector2> {


    private final PhysicsComponent physicsComponent;
    private float maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration;

        /*
        mAA >= mAS^2/2π must be satisfied for steering to be controllable at maximum speed.
        mAS = 2π mAA = 2π is therefore the fastest controllable turning.
        if mAA > mAS then turning may overshoot.
        As mAA / mAS -> ∞ and FPS -> 0, overshoot chance and magnitude will worsen.
     */
        public SteerableComponent(
                PhysicsComponent physicsComponent,
                float maxLinearSpeed,
                float maxLinearAcceleration,
                float maxAngularSpeed,
                float maxAngularAcceleration
        ){
            this.physicsComponent = physicsComponent;
            this.maxLinearSpeed = maxLinearSpeed;
            this.maxLinearAcceleration = maxLinearAcceleration;
            this.maxAngularSpeed = maxAngularSpeed;
            this.maxAngularAcceleration = maxAngularAcceleration;
        }

    @Override
    public Vector2 getLinearVelocity() {
        return physicsComponent.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return physicsComponent.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return 0;
    }

    @Override
    public boolean isTagged() {
        return false;
    }

    @Override
    public void setTagged(boolean tagged) {

    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return 0;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {

    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector2 getPosition() {
        return physicsComponent.getGlobalCenterOfMass();
    }

    @Override
    public float getOrientation() {
        return physicsComponent.getAngleRad();
    }



    @Override
    public void setOrientation(float orientation) {
        physicsComponent.setTransform(physicsComponent.getPosition(), orientation);
    }

        /**
         *
         * @param vector
         * @return Returns the angle in radians pointing along the specified vector.
         */
    @Override
    public float vectorToAngle(Vector2 vector) {
        return vector.angleRad();
    }

        /**
         *
         * @param outVector
         * @param angle
         * @return Returns the unit vector in the direction of the specified angle expressed in radians.
         */
    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = MathUtils.cos(angle);
        outVector.y = MathUtils.sin(angle);
        return outVector;
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }
}
