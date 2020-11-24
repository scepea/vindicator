package com.scepea.vindicator.physics.contact;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;

import javax.inject.Inject;

/**
 * Listens for contact events and generates contact components for the ECS system where appropriate to do so.
 */
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

            if (entityA.getComponent(ContactActionComponent.class) != null)
                entityA.add(new ContactComponent(entityB));

            if (entityB.getComponent(ContactActionComponent.class) != null)
                entityB.add(new ContactComponent(entityA));

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
