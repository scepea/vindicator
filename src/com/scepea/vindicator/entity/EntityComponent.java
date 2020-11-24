package com.scepea.vindicator.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * Generic component for a single entity relationship.
 */
public abstract class EntityComponent implements Component {

    private Entity entity;

    public EntityComponent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
