package com.scepea.vindicator.movement;

import com.scepea.vindicator.utils.Vector2Limiter;

public class AccelerationLimiterComponent {

    private final Vector2Limiter vector2Limiter;

    public AccelerationLimiterComponent(Vector2Limiter vector2Limiter) {
        this.vector2Limiter = vector2Limiter;
    }

    public Vector2Limiter getVectorLimiter() {
        return vector2Limiter;
    }
}
