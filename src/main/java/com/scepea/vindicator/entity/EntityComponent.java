package com.scepea.vindicator.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public abstract class EntityComponent implements Component {

    private final Entity entity;

    public EntityComponent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
