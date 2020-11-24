package com.scepea.vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.math.Vector;

import javax.inject.Inject;

public class SentinelBehaviourFactory<T extends Vector<T>> {

    private final FaceFactory<T> faceFactory;

    @Inject
    public SentinelBehaviourFactory(FaceFactory<T> faceFactory) {
        this.faceFactory = faceFactory;
    }

    public SentinelBehaviour create(Steerable<T> owner, T aimToPoint){
        return new SentinelBehaviour(owner,
                aimToPoint);
    }

    public class SentinelBehaviour extends SteeringBehavior<T> {

        private final Face<T> face;

        private SentinelBehaviour(Steerable<T> owner, T aimToPoint) {
            super(owner);

            face = faceFactory.create(owner,
                    new SteerableAdapter<T>() {
                @Override
                public T getPosition() {
                    return aimToPoint;
                }
            }
            );

        }

        @Override
        protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
            steeringAcceleration.setZero();
            face.calculateSteering(steeringAcceleration);
            return steeringAcceleration;
        }
    }
}