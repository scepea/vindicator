package com.scepea.vindicator.render.hud;

import com.badlogic.ashley.core.Component;
import com.scepea.vindicator.transform.PrimitiveTransform;

public class HudTransformComponent implements Component {
    private final PrimitiveTransform primitiveTransform;

    public HudTransformComponent(PrimitiveTransform primitiveTransform) {
        this.primitiveTransform = primitiveTransform;
    }

    public PrimitiveTransform getTransform() {
        return primitiveTransform;
    }

}
