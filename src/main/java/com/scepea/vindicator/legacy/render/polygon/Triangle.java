package com.scepea.vindicator.legacy.render.polygon;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

/**
 * @deprecated Is only used as part of the polygon rendering system which in itself is deprecated.
 */
@Deprecated
public class Triangle implements Shape2D {

    private float[] vertices;

    public Triangle(){
        vertices = new float[6];
    }

    public Triangle(float[] vertices){
        if (vertices.length == 6){
            this.vertices = vertices;
        } else {
            throw new IllegalArgumentException("A triangle must have three vertices.");
        }
    }

    @Override
    public boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }

    @Override
    public boolean contains(float x, float y) {
        for (int i = 0; i < vertices.length; i += 2){
            if (vertices[i] == x && vertices[i + 1] == y) return true;
        }
        return false;
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

}
