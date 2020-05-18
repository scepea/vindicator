package com.scepea.vindicator.render;

import com.badlogic.ashley.core.Entity;

import javax.inject.Inject;
import java.util.Comparator;

public class LayerComparator implements Comparator<Entity> {

    @Inject
    public LayerComparator() {
    }

    @Override
    public int compare(Entity o1, Entity o2) {
        LayerComponent layerComponent1 = o1.getComponent(LayerComponent.class);
        LayerComponent layerComponent2 = o2.getComponent(LayerComponent.class);

        int depth1 = layerComponent1 == null ? -1 : layerComponent1.getDepth();
        int depth2 = layerComponent2 == null ? -1 : layerComponent2.getDepth();

        return depth1 - depth2;
    }

}
