package com.scepea.vindicator.ai.behaviourTree;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class TargetComponent implements Component {

    private final Entity entity;

    public TargetComponent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
