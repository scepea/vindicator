package com.scepea.vindicator.utils;

import com.badlogic.gdx.math.Vector2;

public class Vector2Limiter extends VectorLimiter<Vector2>{

    private final float forward;
    private final float backward;
    private final float left;
    private final float right;

    public Vector2Limiter(float forward, float backward, float left, float right) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
    }

    @Override
    public Vector2 limit(Vector2 direction, Vector2 candidate) {
        candidate.rotateRad(-direction.angleRad());

        float xLimited;
        if (candidate.x > 0) {
            xLimited = forward / candidate.x;
        } else if (candidate.x < 0){
            xLimited = -backward / candidate.x;
        } else {
            xLimited = 0;
        }

        float yLimited;
        if (candidate.y > 0) {
            yLimited = left / candidate.y;
        } else if (candidate.y < 0) {
            yLimited = -right / candidate.y;
        } else {
            yLimited = 0;
        }

        return candidate.scl(Math.min(xLimited,yLimited)).rotateRad(direction.angleRad());
    }

    @Override
    public float maxAllowable() {
        return Math.max(Math.max(forward, backward), Math.max(left, right));
    }


}
