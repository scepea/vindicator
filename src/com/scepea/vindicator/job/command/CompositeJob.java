package com.scepea.vindicator.job.command;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;

import java.util.Arrays;
import java.util.List;

public class CompositeJob extends EntityJob {

    private List<EntityJob> entityJobs;

    public CompositeJob(Entity owner, JobPriority jobPriority, EntityJob... entityJobs) {
        super(owner, jobPriority);
        this.entityJobs = Arrays.asList(entityJobs);
    }

    @Override
    public Status schedule() {
        Status returnStatus = Status.SUCCEEDED;
        for (EntityJob command : entityJobs){
            if (command.schedule() == Status.FAILED) returnStatus = Status.FAILED;
        }
        return returnStatus;
    }

    @Override
    public Status execute(float deltaTime) {
        Status returnStatus = Status.SUCCEEDED;
        for (EntityJob command : entityJobs){
            if (command.execute(deltaTime) == Status.FAILED) returnStatus = Status.FAILED;
        }
        return returnStatus;
    }

    @Override
    public Status cancel() {
        Status returnStatus = Status.SUCCEEDED;
        for (EntityJob command : entityJobs){
            if (command.cancel() == Status.FAILED) returnStatus = Status.FAILED;
        }
        return returnStatus;
    }

}
