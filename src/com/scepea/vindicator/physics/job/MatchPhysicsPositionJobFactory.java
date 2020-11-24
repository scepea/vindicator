package com.scepea.vindicator.physics.job;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.entity.EntityOwnershipService;
import com.scepea.vindicator.job.Status;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriority;
import com.scepea.vindicator.physics.box2d.Box2dPhysicsComponent;
import com.scepea.vindicator.transform.PrimitiveTransform;

import javax.inject.Inject;

public class MatchPhysicsPositionJobFactory {

    private final EntityOwnershipService entityOwnershipService;

    @Inject
    public MatchPhysicsPositionJobFactory(EntityOwnershipService entityOwnershipService) {
        this.entityOwnershipService = entityOwnershipService;
    }

    public MatchPhysicsPositionJob create(Entity owner, PrimitiveTransform primitiveTransform){
        return new MatchPhysicsPositionJob(owner, primitiveTransform);
    }

    public class MatchPhysicsPositionJob extends EntityJob {

        private final PrimitiveTransform primitiveTransform;
        private final Vector2 calc;

        private MatchPhysicsPositionJob(Entity owner, PrimitiveTransform primitiveTransform) {
            super(owner, JobPriority.PRE_PROCESSING);
            this.primitiveTransform = primitiveTransform;
            calc = new Vector2();
        }

        @Override
        public Status execute(float deltaTime) {
            Box2dPhysicsComponent parentBox2dPhysicsComponent = entityOwnershipService.getNextMatchingComponentInBranch(owner, Box2dPhysicsComponent.class).get();

            calc.set(primitiveTransform.getPosition()).rotate(parentBox2dPhysicsComponent.getAngleVal()).add(parentBox2dPhysicsComponent.getWorldCenter());

            owner.getComponent(Box2dPhysicsComponent.class).setTransformCOM(calc, owner.getComponent(Box2dPhysicsComponent.class).getAngleVal());

            return Status.SUCCEEDED;
        }
    }
}