package com.scepea.vindicator.physics.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.apache.commons.lang3.mutable.MutableFloat;

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

    public Vector2 getWorldCenter(){
        return getBody().getWorldCenter();
    }

    public Vector2 getLocalCenterOfMass(){
        return getBody().getLocalCenter();
    }

    public Vector2 getLinearVelocity(){
        return getBody().getLinearVelocity();
    }

    public float getAngularVelocity (){
        return getBody().getAngularVelocity();
    }

    public float getAngleVal(){
        return body.getAngle();
    }

    public void setTransform (Vector2 position, float angle){
        getBody().setTransform(position, angle);
    }

    public void setTransformCOM(Vector2 position, float angle){
        getBody().setTransform(position.cpy().sub(getBody().getLocalCenter().rotateRad(angle)), angle);
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public void setPosition(Vector2 position){
        setTransform(position, getAngleVal());
    }

}
