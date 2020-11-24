package com.scepea.vindicator.transform;

import com.badlogic.gdx.math.Vector2;
import org.apache.commons.lang3.mutable.MutableFloat;

public class PrimitiveTransform extends Transform{
    protected Vector2 position;
    protected float rotation;

    public PrimitiveTransform(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public PrimitiveTransform(Vector2 position, MutableFloat rotation) {
        this(position, rotation.getValue());
    }

    public PrimitiveTransform(PrimitiveTransform primitiveTransform){
        this.position = primitiveTransform.getPosition().cpy();
        this.rotation = primitiveTransform.getRotation();
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }


}
