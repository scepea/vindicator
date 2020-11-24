package com.scepea.vindicator.ai.steering;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.physics.box2d.Box2dPhysicsComponent;

public class Box2dSteerable implements Steerable<Vector2> {
    private final Box2dPhysicsComponent box2dPhysicsComponent;
    private float maxLinearSpeed;
    private float maxLinearAcceleration;
    private float maxAngularSpeed;
    private float maxAngularAcceleration;

    public Box2dSteerable(Box2dPhysicsComponent box2dPhysicsComponent, float maxLinearSpeed, float maxLinearAcceleration, float maxAngularSpeed, float maxAngularAcceleration) {
        this.box2dPhysicsComponent = box2dPhysicsComponent;
        this.maxLinearSpeed = maxLinearSpeed;
        this.maxLinearAcceleration = maxLinearAcceleration;
        this.maxAngularSpeed = maxAngularSpeed;
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return box2dPhysicsComponent.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return box2dPhysicsComponent.getAngularVelocity();
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
        return box2dPhysicsComponent.getWorldCenter();
    }

    @Override
    public float getOrientation() {
        return box2dPhysicsComponent.getAngleVal();
    }

    @Override
    public void setOrientation(float orientation) {
        box2dPhysicsComponent.setTransform(box2dPhysicsComponent.getPosition(), orientation);
    }

    /**
     * @param vector
     * @return Returns the angle in radians pointing along the specified vector.
     */
    @Override
    public float vectorToAngle(Vector2 vector) {
        return vector.angleRad();
    }

    /**
     * @param outVector
     * @param angle
     * @return Returns the unit vector in the direction of the specified angle expressed in radians.
     */
    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.set(1,0).rotateRad(angle);
        return outVector;
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }
}