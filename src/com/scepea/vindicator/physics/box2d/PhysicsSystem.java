package com.scepea.vindicator.physics.box2d;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

public class PhysicsSystem extends EntitySystem {

    private final World world;

    @Inject
    public PhysicsSystem(World world){
        this.priority = SystemPriority.POLLING.ordinal();
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        world.step(deltaTime, 6, 2);
    }

}
