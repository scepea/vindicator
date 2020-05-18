package com.scepea.vindicator.cleanUp;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.World;
import com.scepea.vindicator.physics.box2d.PhysicsComponent;

import javax.inject.Inject;
import java.util.Stack;

public class DeleteEntitySystem  extends EntitySystem {

    private final Engine engine;
    private final World world;

    private ImmutableArray<Entity> entities;

    @Inject
    public DeleteEntitySystem(Engine engine, World world) {
        this.engine = engine;
        this.world = world;
        priority = Integer.MAX_VALUE - 1;
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(DeleteComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        Stack<Entity> stack = new Stack<>();
        for (Entity entity : entities) {
            stack.push(entity);
        }

        DependantEntitiesComponent dependantEntitiesComponent;
        Entity working;
        while(!stack.isEmpty()){
            working = stack.pop();
            dependantEntitiesComponent = working.getComponent(DependantEntitiesComponent.class);
            if (dependantEntitiesComponent != null){
                for (Entity dependant: dependantEntitiesComponent.getDependantEntities()){
                    stack.push(dependant);
                }
            }
            PhysicsComponent physicsComponent = working.getComponent(PhysicsComponent.class);
            if (physicsComponent != null) {
                world.destroyBody(physicsComponent.getBody());
            }
            working.removeAll();
            engine.removeEntity(working);
        }

    }
}
