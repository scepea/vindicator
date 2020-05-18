package com.scepea.vindicator.physics.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.scepea.vindicator.physics.box2d.physicsBodyEditor.BodyEditorLoader;

import javax.inject.Inject;

public class PhysicsComponentBuilder {

    private final World world;

    private BodyType bodyType = BodyType.StaticBody; // Matches BodyDef#type
    private float linearDampening = 0; // Matches BodyDef#linearDampening
    private float angularDamping = 0; // Matches BodyDef#angularDamping

    private float density = 0; // Matches FixtureDef#density

    private boolean isBullet;

    @Inject
    public PhysicsComponentBuilder(World world){
        this.world = world;
    }

    public PhysicsComponentBuilder withBodyType(BodyType bodyType){
        this.bodyType = bodyType;
        return this;
    }

    public PhysicsComponentBuilder withDensity(float density){
        this.density = density;
        return this;
    }

    public PhysicsComponentBuilder withLinearDampening(float linearDampening){
        this.linearDampening = linearDampening;
        return this;
    }

    public PhysicsComponentBuilder withAngularDampening(float angularDampening){
        this.angularDamping = angularDampening;
        return this;
    }

    public PhysicsComponentBuilder withBullet(boolean isBullet){
        this.isBullet = isBullet;
        return this;
    }

    /**
     * Builds a PhysicsComponent based on this builder's field values. All values passed in are mandatory.
     * @deprecated Use {@link PhysicsComponentBuilder#build(Entity entity, Polygon[] shapes)} instead
     * @param shape The polygonal shape of the body.
     * @return
     */
    @Deprecated
    public PhysicsComponent build(Entity entity, Polygon shape){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.linearDamping = linearDampening;
        bodyDef.angularDamping = angularDamping;

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(shape.getVertices());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = density;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(entity);
        body.setBullet(isBullet);

        return new PhysicsComponent(body);
    }

    /**
     * Builds a PhysicsComponent based on this builder's field values. All values passed in are mandatory.
     * @deprecated Should use {@link PhysicsComponentBuilder#build(Entity, FileHandle, String)} instead.
     * @param shapes The convex polygons that make up the body's overall shape.
     * @return
     */
    @Deprecated
    public PhysicsComponent build(Entity entity, Polygon[] shapes){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.linearDamping = linearDampening;
        bodyDef.angularDamping = angularDamping;

        Body body = world.createBody(bodyDef);
        for (Polygon shape : shapes) {
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.set(shape.getVertices());

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygonShape;
            fixtureDef.density = density;

            body.createFixture(fixtureDef);
        }
        body.setUserData(entity);
        body.setBullet(isBullet);

        return new PhysicsComponent(body);
    }


    public PhysicsComponent build(Entity entity, FileHandle fileHandle, String name){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.linearDamping = linearDampening;
        bodyDef.angularDamping = angularDamping;

        Body body = world.createBody(bodyDef);
        body.setUserData(entity);
        body.setBullet(isBullet);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;

        BodyEditorLoader loader = new BodyEditorLoader(fileHandle);

        loader.attachFixture(body, name, fixtureDef, 1);

        return new PhysicsComponent(body);
    }

}
