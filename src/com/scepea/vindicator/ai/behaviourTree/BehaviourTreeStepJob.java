package com.scepea.vindicator.ai.behaviourTree;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;

public class BehaviourTreeStepJob extends EntityJob {

    public BehaviourTreeStepJob(Entity owner) {
        super(owner, JobPriority.PLANNING);
    }

    @Override
    public Status execute(float deltaTime) {
        owner.getComponent(BehaviourTreeComponent.class).getBehaviorTree().step();
        return Status.SUCCEEDED;
    }
}
