package com.scepea.vindicator.transform;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

public abstract class Transform {

    public abstract Vector2 getPosition();

    public abstract float getRotation();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transform transform = (Transform) o;
        return Float.compare(transform.getRotation(), getRotation()) == 0 &&
                Objects.equals(getPosition(), transform.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getRotation());
    }
}
