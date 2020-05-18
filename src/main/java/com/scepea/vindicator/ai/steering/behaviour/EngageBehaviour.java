package com.scepea.vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.LookWhereYouAreGoing;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;

public class EngageBehaviour <T extends Vector<T>> extends SteeringBehavior<T> {

    private final SteeringAcceleration<T> tempSteeringAcceleration;
    private final Arrive<T> arrive;
    private final Face<T> face;

    public EngageBehaviour(Steerable<T> owner, Steerable<T> target, T aimToPoint) {
        super(owner);

        tempSteeringAcceleration = new SteeringAcceleration<>(owner.getPosition().cpy().setZero());

        float linearDecelerationRadius = (owner.getMaxLinearSpeed() * owner.getMaxLinearSpeed()) / (2 * owner.getMaxLinearAcceleration());

        arrive = new Arrive<>(owner, target);
        arrive.setDecelerationRadius(linearDecelerationRadius);
        arrive.setArrivalTolerance(linearDecelerationRadius * 1/100f);

        float angularDecelerationRadius = (owner.getMaxAngularSpeed() * owner.getMaxAngularSpeed()) / (2 * owner.getMaxAngularAcceleration());

        LookWhereYouAreGoing<T> lookWhereYouAreGoing = new LookWhereYouAreGoing<>(owner);
        lookWhereYouAreGoing.setDecelerationRadius(angularDecelerationRadius);
        lookWhereYouAreGoing.setAlignTolerance(0.1f * MathUtils.degreesToRadians);

        face = new Face<T>(owner,  new SteerableAdapter<T>() {
            @Override
            public T getPosition(){
                return aimToPoint;
            }
        });

        face.setDecelerationRadius(angularDecelerationRadius);
        face.setAlignTolerance(0.1f * MathUtils.degreesToRadians);
    }


    @Override
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();

        arrive.calculateSteering(tempSteeringAcceleration);

        steeringAcceleration.add(tempSteeringAcceleration);

        face.calculateSteering(tempSteeringAcceleration);
        steeringAcceleration.add(tempSteeringAcceleration);

        return steeringAcceleration;
    }
}
