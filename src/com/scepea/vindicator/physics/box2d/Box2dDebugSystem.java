package com.scepea.vindicator.physics.box2d;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import javax.inject.Inject;

/**
 * System wrapper for the {@link Box2DDebugRenderer} which allows Box2D fixtures to be rendered as shapes.
 */
public class Box2dDebugSystem extends EntitySystem {

    private final World world;
    private final OrthographicCamera orthographicCamera;

    @Inject
    public Box2dDebugSystem(World world, OrthographicCamera orthographicCamera) {
        this.world = world;
        this.orthographicCamera = orthographicCamera;
        priority = Integer.MAX_VALUE;
    }

    @Override
    public void addedToEngine(Engine engine){
    }

    @Override
    public void update(float deltaTime) {
        Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.render(world , orthographicCamera.combined);
    }

}