package com.scepea.vindicator.ai.steering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;

public class Steerable2dComponent implements Component {

    private final Steerable<Vector2> steerable ;

    /*
    mAA >= mAS^2/2π must be satisfied for steering to be controllable at maximum speed.
    mAS = 2π mAA = 2π is therefore the fastest controllable turning.
    if mAA > mAS then turning may overshoot.
    As mAA / mAS -> ∞ and FPS -> 0, overshoot chance and magnitude will worsen.
 */
    public Steerable2dComponent(Steerable<Vector2> steerable){
        this.steerable = steerable;
    }

    public Steerable<Vector2> getSteerable() {
        return steerable;
    }
}
