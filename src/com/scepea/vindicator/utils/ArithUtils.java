package com.scepea.vindicator.utils;

import com.badlogic.gdx.ai.utils.ArithmeticUtils;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;

public class ArithUtils {

    @Inject public ArithUtils() {}

    public float angleToTarget(float projectileSpeed, float targetSpeed, float relativeTargetAngle){
        return (float) Math.asin(targetSpeed * (MathUtils.sin(relativeTargetAngle) / projectileSpeed));
    }

    public float relativeAngle(float ownerAngle, float targetAngle){
        return Math.abs(ArithmeticUtils.wrapAngleAroundZero(targetAngle - ownerAngle));
    }

    public float angleBetweenVectors(Vector2 x, Vector2 y){
        return (float) Math.acos(x.dot(y) / (x.len() * y.len()));
    }

    public float limit (float x, float u, float l) {
        return Math.max(Math.min(x, u), Math.max(x, l));
    }
}
