package com.scepea.vindicator.render.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {

    private final Sprite sprite;

    public SpriteComponent(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
