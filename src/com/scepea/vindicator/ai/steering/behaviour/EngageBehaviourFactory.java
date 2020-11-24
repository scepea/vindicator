package com.scepea.vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.math.Vector;
import com.scepea.vindicator.utils.VectorLimiter;

import javax.inject.Inject;

public class EngageBehaviourFactory<T extends Vector<T>> {

    private final FaceFactory<T> faceFactory;

    @Inject
    public EngageBehaviourFactory(FaceFactory<T> faceFactory) {
        this.faceFactory = faceFactory;
    }

    public EngageBehaviour create(Steerable<T> owner, VectorLimiter<T> velocityLimiter, VectorLimiter<T> accelerationLimiter, Steerable<T> target, T aimToPoint){
        return new EngageBehaviour(
                owner,
                velocityLimiter,
                accelerationLimiter,
                target,
                aimToPoint);
    }

    public class EngageBehaviour extends SteeringBehavior<T> {

        private final SteeringAcceleration<T> tempSteeringAcceleration;
        private final Arrive<T> arrive;
        private final Face<T> face;
        private final Steerable<T> target;
        private final VectorLimiter<T> velocityLimiter;
        private final VectorLimiter<T> accelerationLimiter;
        private final T ownerDirector;
        private final T toTarget;


        private EngageBehaviour(Steerable<T> owner, VectorLimiter<T> velocityLimiter, VectorLimiter<T> accelerationLimiter, Steerable<T> target, T aimToPoint) {
            super(owner);
            ownerDirector = owner.getPosition().cpy().setZero();
            toTarget = owner.getPosition().cpy().setZero();

            tempSteeringAcceleration = new SteeringAcceleration<>(owner.getPosition().cpy().setZero());
            this.target = target;
            this.velocityLimiter = velocityLimiter;
            this.accelerationLimiter = accelerationLimiter;


            arrive = new Arrive<>(owner, target);
            arrive.timeToTarget = 1/60f;
            arrive.setArrivalTolerance(0.01f);


            face = faceFactory.create(owner, new SteerableAdapter<T>() {
                @Override
                public T getPosition() {
                    return aimToPoint;
                }
            });

        }


        @Override
        protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
            steeringAcceleration.setZero();

            toTarget.set(target.getPosition()).sub(owner.getPosition());
            owner.angleToVector(ownerDirector, owner.getOrientation());

            owner.setMaxLinearSpeed(velocityLimiter.limit(ownerDirector, toTarget.nor().scl(velocityLimiter.maxAllowable())).len());
            owner.setMaxLinearAcceleration(accelerationLimiter.limit(ownerDirector, toTarget.nor().scl(accelerationLimiter.maxAllowable())).len());

            float linearDecelerationRadius = (owner.getMaxLinearSpeed() * owner.getMaxLinearSpeed()) / (2 * owner.getMaxLinearAcceleration());
            arrive.setDecelerationRadius(linearDecelerationRadius);

            arrive.calculateSteering(tempSteeringAcceleration);

            steeringAcceleration.add(tempSteeringAcceleration);

            face.calculateSteering(tempSteeringAcceleration);
            steeringAcceleration.add(tempSteeringAcceleration);

            return steeringAcceleration;
        }
    }
}