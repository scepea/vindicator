package com.scepea.vindicator.render.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Represents the texture region associated with an orientation based animation.
 */
public class OrientableSpriteComponent implements Component {

    private final Array<TextureAtlas.AtlasRegion> textureRegions;

    public OrientableSpriteComponent(Array<TextureAtlas.AtlasRegion> textureRegions) {
        this.textureRegions = textureRegions;
    }

    public Array<TextureAtlas.AtlasRegion> getTextureRegions() {
        return textureRegions;
    }
}
