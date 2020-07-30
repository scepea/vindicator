package vindicator.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.physics.WorldScale;

import javax.inject.Inject;

public class PointerService {

    private final WorldScale worldScale;
    private final OrthographicCamera orthographicCamera;
    private final Vector2 position;

    @Inject
    public PointerService(WorldScale worldScale, OrthographicCamera orthographicCamera){
        this.worldScale = worldScale;
        this.orthographicCamera = orthographicCamera;
        position = new Vector2();
    }

    private float getWorldX(){
        return convertScreenToWorldForX(Gdx.input.getX());
    }

    private float getWorldY(){
        return convertScreenToWorldForY(Gdx.input.getY());
    }

    private float convertScreenToWorldForX(Integer screenX){
        return ((float)-Gdx.graphics.getWidth() / 2 + screenX) / worldScale.getPixelsPerUnit() + orthographicCamera.position.x;
    }

    private float convertScreenToWorldForY(Integer screenY){
        return ((float) Gdx.graphics.getHeight() / 2 - screenY) / worldScale.getPixelsPerUnit()  + orthographicCamera.position.y;
    }


    public Vector2 getPosition() {
        return position.set(getWorldX(), getWorldY());
    }

}
