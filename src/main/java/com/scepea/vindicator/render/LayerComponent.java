package com.scepea.vindicator.render;

import com.badlogic.ashley.core.Component;

public class LayerComponent implements Component {

    private final int depth;

    public LayerComponent(Enum<?> depth) {
        this.depth = depth.ordinal();
    }

    public int getDepth() {
        return depth;
    }
}
