package vindicator.render.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.inject.Inject;

public class SpriteComponentBuilder {

    private float scale = 100f;
    private Color tint;
    private boolean isCentered = false;

    @Inject public SpriteComponentBuilder(){}

    public SpriteComponentBuilder withScale(float scale){
        this.scale = scale;
        return this;
    }

    public SpriteComponentBuilder withTint(Color tint){
        this.tint = tint;
        return this;
    }

    public SpriteComponentBuilder withCentered(boolean isCentered){
        this.isCentered = isCentered;
        return this;
    }

    public SpriteComponent build(Texture texture){
        Sprite sprite = new Sprite(texture);

        return buildHelper(sprite);
    }

    public SpriteComponent build(TextureRegion texture){
        Sprite sprite = new Sprite(texture);

        return buildHelper(sprite);
    }

    private SpriteComponent buildHelper(Sprite sprite) {
        sprite.setBounds(0, 0, sprite.getWidth() / scale, sprite.getHeight() / scale);
        if (tint != null) {
            sprite.setColor(tint);
        }
        if (isCentered) {
            sprite.setOriginCenter();
        } else {
            sprite.setOrigin(0, 0);
        }

        return new SpriteComponent(sprite);
    }

}
