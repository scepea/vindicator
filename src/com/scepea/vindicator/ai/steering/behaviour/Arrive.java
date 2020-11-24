package com.scepea.vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/**
 * Replacement for {@link com.badlogic.gdx.ai.steer.behaviors.Arrive} which uses a sensible scaling between distance and velocity.
 * @param <T>
 */
public class Arrive<T extends Vector<T>> extends SteeringBehavior<T>

    {

        /** The target to arrive to. */
        protected Location<T> target;

        /** The tolerance for arriving at the target. It lets the owner get near enough to the target without letting small errors keep
         * it in motion. */
        protected float arrivalTolerance;

        /** The radius for beginning to slow down */
        protected float decelerationRadius;

        /** The time over which to achieve target speed */
        protected float timeToTarget = 0.1f;

        /** Creates an {@code Arrive} behavior for the specified owner.
         * @param owner the owner of this behavior */
	public Arrive (Steerable<T> owner) {
        this(owner, null);
    }

        /** Creates an {@code Arrive} behavior for the specified owner and target.
         * @param owner the owner of this behavior
         * @param target the target of this behavior */
	public Arrive (Steerable<T> owner, Location<T> target) {
        super(owner);
        this.target = target;
    }

        @Override
        protected SteeringAcceleration<T> calculateRealSteering (SteeringAcceleration<T> steering) {
        return arrive(steering, target.getPosition());
    }

        protected SteeringAcceleration<T> arrive (SteeringAcceleration<T> steering, T targetPosition) {
        // Get the direction and distance to the target
        T toTarget = steering.linear.set(targetPosition).sub(owner.getPosition());
        float distance = toTarget.len();

        // Check if we are there, return no steering
        if (distance <= arrivalTolerance) return steering.setZero();

        Limiter actualLimiter = getActualLimiter();
        // Go max speed
        float targetSpeed = actualLimiter.getMaxLinearSpeed();

        // If we are inside the slow down radius calculate a scaled speed
        if (distance <= decelerationRadius) targetSpeed =
                (float) Math.sqrt(actualLimiter.getMaxLinearSpeed() * actualLimiter.getMaxLinearSpeed() - 2 * actualLimiter.getMaxLinearAcceleration() * (decelerationRadius - distance));

        // Target velocity combines speed and direction
        T targetVelocity = toTarget.scl(targetSpeed / distance); // Optimized code for: toTarget.nor().scl(targetSpeed)

        // Acceleration tries to get to the target velocity without exceeding max acceleration
        // Notice that steering.linear and targetVelocity are the same vector
        targetVelocity.sub(owner.getLinearVelocity()).scl(1f / timeToTarget).limit(actualLimiter.getMaxLinearAcceleration());

        // No angular acceleration
        steering.angular = 0f;

        // Output the steering
        return steering;
    }

        /** Returns the target to arrive to. */
        public Location<T> getTarget () {
        return target;
    }

        /** Sets the target to arrive to.
         * @return this behavior for chaining. */
        public Arrive<T> setTarget (Location<T> target) {
        this.target = target;
        return this;
    }

        /** Returns the tolerance for arriving at the target. It lets the owner get near enough to the target without letting small
         * errors keep it in motion. */
        public float getArrivalTolerance () {
        return arrivalTolerance;
    }

        /** Sets the tolerance for arriving at the target. It lets the owner get near enough to the target without letting small errors
         * keep it in motion.
         * @return this behavior for chaining. */
        public Arrive<T> setArrivalTolerance (float arrivalTolerance) {
        this.arrivalTolerance = arrivalTolerance;
        return this;
    }

        /** Returns the radius for beginning to slow down. */
        public float getDecelerationRadius () {
        return decelerationRadius;
    }

        /** Sets the radius for beginning to slow down.
         * @return this behavior for chaining. */
        public Arrive<T> setDecelerationRadius (float decelerationRadius) {
        this.decelerationRadius = decelerationRadius;
        return this;
    }

        /** Returns the time over which to achieve target speed. */
        public float getTimeToTarget () {
        return timeToTarget;
    }

        /** Sets the time over which to achieve target speed.
         * @return this behavior for chaining. */
        public Arrive<T> setTimeToTarget (float timeToTarget) {
        this.timeToTarget = timeToTarget;
        return this;
    }

        //
        // Setters overridden in order to fix the correct return type for chaining
        //

        @Override
        public Arrive<T> setOwner (Steerable<T> owner) {
        this.owner = owner;
        return this;
    }

        @Override
        public Arrive<T> setEnabled (boolean enabled) {
        this.enabled = enabled;
        return this;
    }

        /** Sets the limiter of this steering behavior. The given limiter must at least take care of the maximum linear speed and
         * acceleration.
         * @return this behavior for chaining. */
        @Override
        public Arrive<T> setLimiter (Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
