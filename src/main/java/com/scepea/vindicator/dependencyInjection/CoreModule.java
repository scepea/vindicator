package com.scepea.vindicator.dependencyInjection;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.scepea.vindicator.physics.WorldScale;
import com.scepea.vindicator.physics.collision.ContactProcessor;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.Random;

@Module
public class CoreModule {

    @Provides
    @Singleton
    ShapeRenderer shapeRenderer() {
        return new ShapeRenderer();
    }

    @Provides
    @Singleton
    SpriteBatch spriteBatch() {
        return new SpriteBatch();
    }

    @Provides
    @Singleton
    Engine engine(){
        return new Engine();
    }

    @Provides
    @Singleton
    public World world(ContactProcessor contactProcessor){
        World world = new World(new Vector2(0,0), true);
        world.setContactListener(contactProcessor);
        return world;
    }

    @Provides
    @Singleton
    public WorldScale pixelsPerMeter(){
        return new WorldScale(100);
    }

    @Provides
    EarClippingTriangulator earClippingTriangulator(){return new EarClippingTriangulator();}

    @Provides
    @Singleton
    OrthographicCamera orthographicCamera(){
        return new OrthographicCamera();
    }

    @Provides
    Random random(){return new Random();}
}
