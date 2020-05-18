package com.scepea.vindicator.physics.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;

import javax.inject.Inject;

public class ContactProcessor implements ContactListener {

    @Inject
    public ContactProcessor(){}

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getBody().getUserData() instanceof Entity && fixtureB.getBody().getUserData() instanceof Entity){
            Entity entityA = (Entity) fixtureA.getBody().getUserData();
            Entity entityB = (Entity) fixtureB.getBody().getUserData();

            entityA.add(new CollisionComponent(entityB));

            entityB.add(new CollisionComponent(entityA));

        } else {
            throw new IllegalArgumentException("Each physics body should know about its containing entity.");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
