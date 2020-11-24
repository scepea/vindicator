package com.scepea.vindicator.cleanUp;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.World;
import com.scepea.vindicator.ai.behaviourTree.TargetService;
import com.scepea.vindicator.entity.EntityOwnershipService;
import com.scepea.vindicator.physics.box2d.Box2dPhysicsComponent;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;
import java.util.Stack;

public class DeleteEntitySystem  extends EntitySystem {

    private final Engine engine;
    private final World world;
    private final EntityOwnershipService entityOwnershipService;
    private final TargetService targetService;

    private ImmutableArray<Entity> entities;

    @Inject
    public DeleteEntitySystem(Engine engine, World world, EntityOwnershipService entityOwnershipService, TargetService targetService) {
        this.engine = engine;
        this.world = world;
        this.entityOwnershipService = entityOwnershipService;
        this.targetService = targetService;
        priority = SystemPriority.CLEANUP.ordinal();
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

        Entity working;
        while(!stack.isEmpty()){
            working = stack.pop();
            for (Entity dependant: entityOwnershipService.getDependants(working)){
                stack.push(dependant);
            }
            Box2dPhysicsComponent box2dPhysicsComponent = working.getComponent(Box2dPhysicsComponent.class);
            if (box2dPhysicsComponent != null) {
                world.destroyBody(box2dPhysicsComponent.getBody());
            }

            targetService.eraseTarget(working);

            working.removeAll();
            engine.removeEntity(working);
        }

    }
}
