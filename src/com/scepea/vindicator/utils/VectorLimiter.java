package com.scepea.vindicator.utils;

import com.badlogic.gdx.math.Vector;

public abstract class VectorLimiter<T extends Vector<T>> {

    public abstract T limit(T direction, T candidate);

    public abstract float maxAllowable();
}
