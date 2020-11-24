package com.scepea.vindicator.job.api;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.component.ExecutionQueueComponent;

public abstract class EntityJob {

    protected final Entity owner;
    protected final JobPriority jobPriority;

    public EntityJob(Entity owner, JobPriority jobPriority) {
        if (owner.getComponent(ExecutionQueueComponent.class) == null){
            throw new IllegalArgumentException("An entity must have an Execution Queue Component to use Entity Jobs.");
        }
        this.owner = owner;
        this.jobPriority = jobPriority;
    }

    /**
     *
     * @param deltaTime
     * @return Status of the job so that any wrapping jobs may process this job accordingly.
     */
    public abstract Status execute(float deltaTime);

    /**
     * Used to cancel the execution of a job. By default this will simply ensure the job is removed from its owner's execution queue.
     * @return
     */
    public Status cancel(){
        owner.getComponent(ExecutionQueueComponent.class).remove(this);
        return Status.SUCCEEDED;
    }

    public Status schedule(){
        owner.getComponent(ExecutionQueueComponent.class).offer(this);
        return Status.SUCCEEDED;
    }

    public Entity getOwner() {
        return owner;
    }

    public JobPriority getJobPriority() {
        return jobPriority;
    }
}
