package com.scepea.vindicator.projectile.vector2;

import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;
import java.util.Optional;

public class InterceptService {

    @Inject
    public InterceptService() {}

    /**
     * Calculates the intercept vector for a projectile of fixed speed and target of fixed velocity.
     * Uses quadratic method to find smallest real (non negative) time and uses this to calculate intercept position from target position and velocity.
     * @param ownerPosition
     * @param ownerVelocity
     * @param targetPosition
     * @param targetVelocity
     * @param projectileSpeed
     * @return An optional vector representing the closest intercept if it exists.
     */
    public Optional<Vector2> calculateInterceptPosition(Vector2 ownerPosition, Vector2 ownerVelocity, Vector2 targetPosition, Vector2 targetVelocity, float projectileSpeed) {
        float drx = targetPosition.x - ownerPosition.x;
        float dry = targetPosition.y - ownerPosition.y;

        float dvx = targetVelocity.x - ownerVelocity.x;
        float dvy = targetVelocity.y - ownerVelocity.y;

        // Calculate coefficients of the quadratic equation.
        float a = dvx * dvx + dvy * dvy - projectileSpeed * projectileSpeed;
        float b = drx * dvx + dry * dvy;
        float c = drx * drx + dry * dry;

        float t;
        if (a == 0) {
            t = -c / (2*b);
        } else {
            final float base = -b / a;

            final float discriminant = base * base - c / a;
            if (discriminant < 0) {
                return Optional.empty();
            }

            final float root = (float) Math.sqrt(discriminant);

            final float t1 = base + root;
            final float t2 = base - root;

            final float minTime = Math.min(t1, t2);
            final float maxTime = Math.max(t1, t2);

            t = minTime > 0 ? minTime : maxTime;

        }

        if (t < 0) {
            return Optional.empty(); // No solution
        } else {
            return Optional.of(new Vector2(targetPosition.x + t * targetVelocity.x, targetPosition.y + t * targetVelocity.y));
        }

    }

    public Optional<Vector2> calculateAimingPoint(Vector2 ownerPosition, Vector2 ownerVelocity, Vector2 targetPosition, Vector2 targetVelocity, float projectileSpeed) {
        float drx = targetPosition.x - ownerPosition.x;
        float dry = targetPosition.y - ownerPosition.y;

        float dvx = targetVelocity.x - ownerVelocity.x;
        float dvy = targetVelocity.y - ownerVelocity.y;

        // Calculate coefficients of the quadratic equation.
        float a = dvx * dvx + dvy * dvy - projectileSpeed * projectileSpeed;
        float b = drx * dvx + dry * dvy;
        float c = drx * drx + dry * dry;

        float t;
        if (a == 0) {
            t = -c / (2*b);
        } else {
            final float base = -b / a;

            final float discriminant = base * base - c / a;
            if (discriminant < 0) {
                return Optional.empty();
            }

            final float root = (float) Math.sqrt(discriminant);

            final float t1 = base + root;
            final float t2 = base - root;

            final float minTime = Math.min(t1, t2);
            final float maxTime = Math.max(t1, t2);

            t = minTime > 0 ? minTime : maxTime;

        }

        if (t < 0) {
            return Optional.empty(); // No solution
        } else {
            return Optional.of(new Vector2(targetPosition.x + t * dvx, targetPosition.y + t * dvy));
        }

    }
}
