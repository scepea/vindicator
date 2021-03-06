package com.scepea.vindicator.ai.steering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public class SteeringBehaviorComponent implements Component {

    private SteeringBehavior<Vector2> steeringBehavior;

    public SteeringBehaviorComponent(SteeringBehavior<Vector2> steeringBehavior) {
        this.setSteeringBehavior(steeringBehavior);
    }

    public SteeringBehavior<Vector2> getSteeringBehavior() {
        return steeringBehavior;
    }

    public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }
}
