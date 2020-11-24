package com.scepea.vindicator.job;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.component.ExecutionQueueComponent;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;
import java.util.PriorityQueue;

public class JobExecutionSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;


    @Inject
    public JobExecutionSystem(){
        super(SystemPriority.ACTIONS.ordinal());
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(ExecutionQueueComponent.class).get());
    }


    @Override
    public void update(float deltaTime) {
        for(Entity entity : entities){
            PriorityQueue<EntityJob> entityJobs = entity.getComponent(ExecutionQueueComponent.class).flush();
            for (EntityJob entityJob : entityJobs){
                entityJob.execute(deltaTime);
            }
        }
    }
}
