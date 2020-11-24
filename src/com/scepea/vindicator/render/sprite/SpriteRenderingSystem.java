package com.scepea.vindicator.render.sprite;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.scepea.vindicator.render.BackgroundComponent;
import com.scepea.vindicator.render.LayerComparator;
import com.scepea.vindicator.render.hud.HudTransformComponent;
import com.scepea.vindicator.render.text.TextComponent;
import com.scepea.vindicator.transform.PrimitiveTransform;
import com.scepea.vindicator.transform.Transform;
import com.scepea.vindicator.transform.component.TransformComponent;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Arrays;

public class SpriteRenderingSystem extends EntitySystem {

    private ImmutableArray<Entity> backgroundImmutableArray;
    private ImmutableArray<Entity> worldSpritesImmutableArray;
    private ImmutableArray<Entity> screenSpritesImmutableArray;
    private ImmutableArray<Entity> screenTextsImmutableArray;


    private final Provider<SpriteBatch> spriteBatchProvider;
    private final LayerComparator layerComparator;
    private final OrthographicCamera orthographicCamera;
    private final Provider<BitmapFont> bitmapFontProvider;


    @Inject
    public SpriteRenderingSystem(
            Provider<SpriteBatch> spriteBatchProvider,
            LayerComparator layerComparator,
            OrthographicCamera orthographicCamera,
            Provider<BitmapFont> bitmapFontProvider
    ){
        this.spriteBatchProvider = spriteBatchProvider;
        this.layerComparator = layerComparator;
        this.orthographicCamera = orthographicCamera;
        this.bitmapFontProvider = bitmapFontProvider;
        priority = SystemPriority.RENDER.ordinal();

    }

    @Override
    public void addedToEngine(Engine engine){
        backgroundImmutableArray = engine.getEntitiesFor(Family.all(SpriteComponent.class, BackgroundComponent.class).get());
        worldSpritesImmutableArray = engine.getEntitiesFor(Family.all(SpriteComponent.class).exclude(BackgroundComponent.class, HudTransformComponent.class).get());
        screenSpritesImmutableArray = engine.getEntitiesFor(Family.all(SpriteComponent.class, HudTransformComponent.class).get());
        screenTextsImmutableArray = engine.getEntitiesFor(Family.all(TextComponent.class, HudTransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        SpriteBatch batch = this.spriteBatchProvider.get();
        batch.begin();
        drawBackground(batch);
        drawWorldSprites(batch);
        drawScreenSprites(batch);
        drawScreenText(batch);
        batch.end();
    }

    private void drawScreenText(SpriteBatch batch) {
        batch.setProjectionMatrix(orthographicCamera.projection);
        for (Entity entity : screenTextsImmutableArray) {
            final PrimitiveTransform primitiveTransform = entity.getComponent(HudTransformComponent.class).getTransform();
            bitmapFontProvider.get().draw(batch, entity.getComponent(TextComponent.class).toString(), primitiveTransform.getPosition().x, primitiveTransform.getPosition().y);
        }
    }

    private void drawScreenSprites(SpriteBatch batch) {
        batch.setProjectionMatrix(orthographicCamera.projection);
        for(Entity entity: screenSpritesImmutableArray){
            Sprite sprite = entity.getComponent(SpriteComponent.class).getSprite();
            {
                final PrimitiveTransform primitiveTransform = entity.getComponent(HudTransformComponent.class).getTransform();
                sprite.setOriginBasedPosition(primitiveTransform.getPosition().x, primitiveTransform.getPosition().y);
                sprite.setRotation(primitiveTransform.getRotation() * MathUtils.radiansToDegrees);
            }
            sprite.draw(batch);
        }
    }

    private void drawWorldSprites(SpriteBatch batch) {
        batch.setProjectionMatrix(orthographicCamera.combined);
        Entity[] sprites = worldSpritesImmutableArray.toArray(Entity.class);
        Arrays.sort(sprites, layerComparator);
        for(Entity entity: sprites){
            Sprite sprite = entity.getComponent(SpriteComponent.class).getSprite();
            {
                final Transform transform = entity.getComponent(TransformComponent.class).getTransform();
                sprite.setOriginBasedPosition(transform.getPosition().x, transform.getPosition().y);
                sprite.setRotation(transform.getRotation() * MathUtils.radiansToDegrees);
            }
            sprite.draw(batch);
        }
    }

    private void drawBackground(SpriteBatch batch) {
        for(Entity entity: backgroundImmutableArray){
            batch.setProjectionMatrix(orthographicCamera.combined);
            Sprite sprite = entity.getComponent(SpriteComponent.class).getSprite();

            final Texture texture = sprite.getTexture();
            final float x = orthographicCamera.position.x;
            final float y = orthographicCamera.position.y;

            batch.draw(
                    texture,
                    x - ((float) Gdx.graphics.getWidth())/200,
                    y -((float)Gdx.graphics.getHeight())/200,
                    (float) texture.getWidth()/100,
                    (float) texture.getHeight()/100,
                    (int)(orthographicCamera.position.x * 100),
                    -(int)(orthographicCamera.position.y * 100),
                    sprite.getTexture().getWidth(),
                    sprite.getTexture().getHeight(),
                    false,
                    false
            );
        }
    }
}
