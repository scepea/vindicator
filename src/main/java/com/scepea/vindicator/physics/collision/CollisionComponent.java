package com.scepea.vindicator.physics.collision;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class CollisionComponent implements Component {
    private final Entity entity;

    public CollisionComponent(Entity collisionEntity) {
        this.entity = collisionEntity;
    }

    public Entity getEntity() {
        return entity;
    }

}
