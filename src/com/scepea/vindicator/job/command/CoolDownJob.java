package com.scepea.vindicator.job.command;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;
import com.scepea.vindicator.job.component.CoolDownComponent;

/**
 * TODO This command can be made more generic if we have a CooldownComponent.
 */
public class CoolDownJob extends EntityJob {

    private CoolDownComponent coolDownComponent;

    public CoolDownJob(Entity owner){
        super(owner, JobPriority.PRE_PROCESSING);
        coolDownComponent = owner.getComponent(CoolDownComponent.class);
        if (coolDownComponent == null) {
            throw new IllegalArgumentException("We must have a cool down component to run a cool down job.");
        }
    }

    @Override
    public Status execute(float deltaTime) {
        coolDownComponent.reduceCoolDown(deltaTime);
        if (coolDownComponent.getCoolDown() >= 0) {
            schedule();
            return Status.RUNNING;
        } else {
            return Status.SUCCEEDED;
        }
    }

}
