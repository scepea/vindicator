package vindicator.physics.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * A component wrapper for a Box2d body with a few utility methods for ease of use.
 */
public class Box2dPhysicsComponent implements Component {

    private final Body body;

    public Box2dPhysicsComponent(Body body){
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public Vector2 getGlobalCenterOfMass(){
        return body.getPosition().cpy().add(body.getLocalCenter().rotateRad(body.getAngle()));
    }

    public Vector2 getLinearVelocity(){
        return body.getLinearVelocity();
    }

    public float getAngularVelocity (){
        return body.getAngularVelocity();
    }

    public float getAngleRad(){
        return body.getAngle();
    }

    public void setTransform (Vector2 position, float angle){
        body.setTransform(position, angle);
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }
}
