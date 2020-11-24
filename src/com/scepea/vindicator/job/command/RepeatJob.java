package com.scepea.vindicator.job.command;

import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;

public class RepeatJob extends EntityJob {

    private final EntityJob command;

    public RepeatJob(EntityJob command) {
        super(command.getOwner(), command.getJobPriority());
        this.command = command;
    }

    @Override
    public Status execute(float deltaTime) {
        Status status = command.execute(deltaTime);
        if (status != Status.FAILED) {
            schedule();
            return Status.RUNNING;
        } else {
            return status;
        }
    }


}