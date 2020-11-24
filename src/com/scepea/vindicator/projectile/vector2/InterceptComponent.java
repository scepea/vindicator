package com.scepea.vindicator.projectile.vector2;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class InterceptComponent implements Component {
    Vector2 intercept;

    public InterceptComponent() {
        this.intercept = new Vector2();
    }

    public Vector2 getIntercept() {
        return intercept;
    }

    public void setIntercept(Vector2 intercept) {
        this.intercept = intercept;
    }
}
