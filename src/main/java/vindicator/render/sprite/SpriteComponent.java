package vindicator.render.sprite;

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

    /**
     * A utility method that provides the longest dimension of the sprite as its length. This has its uses for things like scaling physics bodies.
     * @return The longest axis of the sprite in pixels.
     */
    public int getLength(){
        return Math.max(sprite.getRegionWidth(), sprite.getRegionHeight());
    }

}
