package com.scepea.vindicator.job;

import com.badlogic.ashley.core.Component;
import com.scepea.vindicator.job.api.EntityJob;

public abstract class JobComponent implements Component {

    private final EntityJob command;

    public JobComponent(EntityJob command) {
        this.command = command;
    }

    public EntityJob getCommand() {
        return command;
    }

}
