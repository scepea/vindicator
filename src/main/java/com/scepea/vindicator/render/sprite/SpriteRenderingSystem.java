package com.scepea.vindicator.render.sprite;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.scepea.vindicator.render.BackgroundComponent;
import com.scepea.vindicator.render.LayerComparator;
import com.scepea.vindicator.transform.Transform;
import com.scepea.vindicator.transform.TransformEntityService;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Arrays;

public class SpriteRenderingSystem extends EntitySystem {

    private ImmutableArray<Entity> spriteImmutableArray;
    private ImmutableArray<Entity> backgroundImmutableArray;
    private final Provider<SpriteBatch> batch;
    private final TransformEntityService transformEntityService;
    private final LayerComparator layerComparator;
    private final OrthographicCamera orthographicCamera;

    @Inject
    public SpriteRenderingSystem(Provider<SpriteBatch> spriteBatch, TransformEntityService transformEntityService, LayerComparator layerComparator, OrthographicCamera orthographicCamera) {
        this.batch = spriteBatch;
        this.transformEntityService = transformEntityService;
        this.layerComparator = layerComparator;
        this.orthographicCamera = orthographicCamera;
        priority = Integer.MAX_VALUE - 1;
    }

    @Override
    public void addedToEngine(Engine engine){
        spriteImmutableArray = engine.getEntitiesFor(Family.all(SpriteComponent.class).exclude(BackgroundComponent.class).get());
        backgroundImmutableArray = engine.getEntitiesFor(Family.all(SpriteComponent.class, BackgroundComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        SpriteBatch batch = this.batch.get();
        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        for(Entity entity: backgroundImmutableArray){
            Sprite sprite = entity.getComponent(SpriteComponent.class).getSprite();

            final Texture texture = sprite.getTexture();
            final float x = orthographicCamera.position.x;
            final float y = orthographicCamera.position.y;

            batch.draw(
                    texture,
                    x - ((float)Gdx.graphics.getWidth())/200,
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

        Entity[] sprites = spriteImmutableArray.toArray(Entity.class);
        Arrays.sort(sprites, layerComparator);
        for(Entity entity: sprites){
            Sprite sprite = entity.getComponent(SpriteComponent.class).getSprite();
            {
                final Transform transform = transformEntityService.calculateGlobalTransform(entity);
                sprite.setOriginBasedPosition(transform.getPosition().x, transform.getPosition().y);
                sprite.setRotation(transform.getRotation() * MathUtils.radiansToDegrees);
            }
            sprite.draw(batch);
        }
        batch.end();
    }
}
