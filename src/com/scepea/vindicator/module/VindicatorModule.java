package com.scepea.vindicator.module;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.scepea.vindicator.physics.WorldScale;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.Random;

@Module
public class VindicatorModule {

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
    public WorldScale pixelsPerMeter(){
        return new WorldScale(100);
    }

    @Provides
    EarClippingTriangulator earClippingTriangulator(){return new EarClippingTriangulator();}

    @Provides
    @Singleton
    OrthographicCamera orthographicCamera(){
        OrthographicCamera orthographicCamera = new OrthographicCamera();
        orthographicCamera.zoom = 1f;
        return orthographicCamera;
    }

    @Provides
    Random random(){return new Random();}

    @Provides
    @Singleton
    BitmapFont bitmapFont(){
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setUseIntegerPositions(false); // Absolutely critical if you want to scale fonts smaller.
        bitmapFont.getData().setScale(0.01f);
        return bitmapFont;
    }
}
