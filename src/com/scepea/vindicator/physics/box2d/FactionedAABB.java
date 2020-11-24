package com.scepea.vindicator.physics.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

import java.util.HashSet;
import java.util.Set;

public class FactionedAABB implements QueryCallback {

    private final Set<Entity> results;

    public FactionedAABB() {
        this.results = new HashSet<>();
    }


    @Override
    public boolean reportFixture(Fixture fixture) {
        if (fixture.getBody().getUserData() instanceof Entity) {
            results.add((Entity) fixture.getBody().getUserData());
        }
        return true;
    }

    public Set<Entity> getResults() {
        return results;
    }
}
