package com.scepea.vindicator.ai.steering.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector2;

public class SteeringAccelerationComponent implements Component {
    private final SteeringAcceleration<Vector2> steeringOutput;

    public SteeringAccelerationComponent() {
        steeringOutput = new SteeringAcceleration<>(new Vector2());
    }

    public SteeringAcceleration<Vector2> getSteeringOutput() {
        return steeringOutput;
    }
}
