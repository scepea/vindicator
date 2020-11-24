package com.scepea.vindicator.job.command;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.entity.EntityComponent;
import com.scepea.vindicator.job.JobComponent;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;

public class DelegateJob extends EntityJob {

    private final Class<? extends EntityComponent> relation;
    private final Class<? extends JobComponent> action;

    public DelegateJob(Entity owner, JobPriority jobPriority, Class<? extends EntityComponent> relation, Class<? extends JobComponent> action) {
        super(owner, jobPriority);
        this.relation = relation;
        this.action = action;
    }

    @Override
    public Status schedule() {
        EntityComponent entityComponent = owner.getComponent(relation);
        if (entityComponent != null) {
            JobComponent jobComponent = entityComponent.getEntity().getComponent(action);
            if (jobComponent != null) {
                return jobComponent.getCommand().schedule();
            }
        }
        return Status.FAILED;
    }

    @Override
    public Status execute(float deltaTime) {
        return Status.FAILED; // This job should never be executed.
    }

    @Override
    public Status cancel(){
        EntityComponent entityComponent = owner.getComponent(relation);
        if (entityComponent != null) {
            JobComponent jobComponent = entityComponent.getEntity().getComponent(action);
            if (jobComponent != null) {
                return jobComponent.getCommand().cancel();
            }
        }
        return Status.FAILED;
    }

}
