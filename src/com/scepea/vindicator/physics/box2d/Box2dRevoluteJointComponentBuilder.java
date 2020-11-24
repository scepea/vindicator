package com.scepea.vindicator.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.scepea.vindicator.physics.box2d.component.Box2dJointComponent;

import javax.inject.Inject;

public class Box2dRevoluteJointComponentBuilder {

    private final World world;

    private float referenceAngle;
    private boolean enableLimit;
    private float lowerAngle;
    private float upperAngle;


    @Inject
    public Box2dRevoluteJointComponentBuilder(World world) {
        this.world = world;
    }

    public Box2dRevoluteJointComponentBuilder withReferenceAngle(float referenceAngle){
        this.referenceAngle = referenceAngle;
        return this;
    }

    public Box2dRevoluteJointComponentBuilder withEnableLimit(boolean enableLimit){
        this.enableLimit = enableLimit;
        return this;
    }

    public Box2dRevoluteJointComponentBuilder withLowerAngle(float lowerAngle){
        this.lowerAngle = lowerAngle;
        return this;
    }

    public Box2dRevoluteJointComponentBuilder withUpperAngle(float upperAngle){
        this.upperAngle = upperAngle;
        return this;
    }


    public Box2dJointComponent build(
            Box2dPhysicsComponent physicsComponentA, Vector2 localAnchorA,
            Box2dPhysicsComponent physicsComponentB, Vector2 localAnchorB
    ){
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();

        revoluteJointDef.bodyA = physicsComponentA.getBody();
        revoluteJointDef.localAnchorA.set(localAnchorA);

        revoluteJointDef.bodyB = physicsComponentB.getBody();
        revoluteJointDef.localAnchorB.set(localAnchorB);

        revoluteJointDef.collideConnected = false;

        revoluteJointDef.referenceAngle = referenceAngle;

        revoluteJointDef.enableLimit = enableLimit;
        if (enableLimit) {
            revoluteJointDef.lowerAngle = lowerAngle;
            revoluteJointDef.upperAngle = upperAngle;
        }

        return new Box2dJointComponent(world.createJoint(revoluteJointDef));
    }



}
