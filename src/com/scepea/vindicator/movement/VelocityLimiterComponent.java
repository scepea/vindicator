package com.scepea.vindicator.movement;

import com.scepea.vindicator.utils.Vector2Limiter;

public class VelocityLimiterComponent {

    private final Vector2Limiter vector2Limiter;

    public VelocityLimiterComponent(Vector2Limiter vector2Limiter) {
        this.vector2Limiter = vector2Limiter;
    }

    public Vector2Limiter getVectorLimiter() {
        return vector2Limiter;
    }
}
