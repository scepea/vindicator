package com.scepea.vindicator.ai.steering;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.ai.steering.component.SteeringAccelerationComponent;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;

public class SteeringBehaviourJob extends EntityJob {

    public SteeringBehaviourJob(Entity owner) {
        super(owner, JobPriority.PLANNING);
    }

    @Override
    public final Status execute(float deltaTime) {
        SteeringAccelerationComponent steeringAccelerationComponent = owner.getComponent(SteeringAccelerationComponent.class);
        SteeringBehaviorComponent steeringBehaviorComponent = owner.getComponent(SteeringBehaviorComponent.class);
        if (steeringBehaviorComponent != null && steeringAccelerationComponent != null && steeringBehaviorComponent.getSteeringBehavior() != null){
            steeringBehaviorComponent.getSteeringBehavior().calculateSteering(steeringAccelerationComponent.getSteeringOutput());
            return Status.RUNNING;
        } else {
            return Status.FAILED;
        }

    }
}
