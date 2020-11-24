package com.scepea.vindicator.job.command;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.job.JobComponent;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;

public class AssignJob extends EntityJob {

    private Class<? extends JobComponent> jobComponentClass;

    public AssignJob(Entity owner, JobPriority jobPriority, Class<? extends JobComponent> jobComponentClass) {
        super(owner, jobPriority);
        this.jobComponentClass = jobComponentClass;
    }

    @Override
    public Status cancel(){
        owner.getComponent(jobComponentClass).getCommand().cancel();
        return Status.SUCCEEDED;
    }

    @Override
    public Status schedule(){
        owner.getComponent(jobComponentClass).getCommand().schedule();
        return Status.SUCCEEDED;
    }

    @Override
    public Status execute(float deltaTime) {
        owner.getComponent(jobComponentClass).getCommand().execute(deltaTime);
        return Status.SUCCEEDED;
    }


}
