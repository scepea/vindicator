package com.scepea.vindicator.transform.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.transform.PrimitiveTransform;
import com.scepea.vindicator.transform.Transform;

public class TransformComponent implements Component {

    private Transform transform;

    public TransformComponent(Transform transform) {
        this.transform = transform;
    }

    @Deprecated
    public TransformComponent(Vector2 position, float rotation) {
        this(new PrimitiveTransform(position, rotation));
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(PrimitiveTransform primitiveTransform) {
        this.transform = primitiveTransform;
    }


}
