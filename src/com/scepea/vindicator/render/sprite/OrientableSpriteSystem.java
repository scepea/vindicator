package com.scepea.vindicator.render.sprite;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.scepea.vindicator.transform.PrimitiveTransform;
import com.scepea.vindicator.transform.Transform;
import com.scepea.vindicator.transform.component.TransformComponent;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

/**
 * Processes orientation based animations prior to rendering.
 */
public class OrientableSpriteSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Inject
    public OrientableSpriteSystem() {
        priority = SystemPriority.DECISIONS.ordinal();
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(OrientableSpriteComponent.class, SpriteComponent.class, TransformComponent.class).get());
    }

    /**
     * Looks at the number of sprites in a texture region and finds the one closest to the entity's rotation.
     * Assumes that the angle between each sprite in the texture region is equal.
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {

            Array<TextureAtlas.AtlasRegion> textureRegions = entity.getComponent(OrientableSpriteComponent.class).getTextureRegions();

            float increment = MathUtils.PI2 / (float) textureRegions.size ;

            final Transform transform = entity.getComponent(TransformComponent.class).getTransform();

            int nRaw = (MathUtils.round(transform.getRotation() / increment))%(textureRegions.size);

            int n = nRaw < 0 ? textureRegions.size + nRaw : nRaw;

            entity.getComponent(SpriteComponent.class).getSprite().setRegion(textureRegions.get(n));
        }
    }

}
