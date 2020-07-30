package vindicator.transform;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

public class Transform {
    private Vector2 position;
    private float rotation;

    public Transform(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setTransform(Vector2 position, float rotation){
        this.position = position;
        this.rotation = rotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transform transform = (Transform) o;
        return Float.compare(transform.rotation, rotation) == 0 &&
                Objects.equals(position, transform.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, rotation);
    }
}
