package com.scepea.vindicator.render.textureAtlas;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasComponent implements Component {

    private final TextureAtlas textureAtlas;

    public TextureAtlasComponent(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }
}
