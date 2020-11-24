package com.scepea.vindicator.transform;

import com.badlogic.gdx.math.Vector2;
import org.apache.commons.lang3.mutable.MutableFloat;

public class CompositeTransform extends Transform{

    private final Transform baseTransform;
    private final Transform additionalTransform;

    private final Vector2 calc;

    public CompositeTransform(Transform baseTransform, Vector2 position, MutableFloat rotation) {
        this(baseTransform, new PrimitiveTransform(position, rotation));
    }

    public CompositeTransform(Transform baseTransform, Transform additionalTransform) {
        this.baseTransform = baseTransform;
        this.additionalTransform = additionalTransform;
        this.calc = new Vector2();
    }

    public Vector2 getPosition() {
        return calc.set(additionalTransform.getPosition()).rotateRad(baseTransform.getRotation()).add(baseTransform.getPosition());
    }

    public float getRotation() {
        return baseTransform.getRotation() + additionalTransform.getRotation();
    }

}
