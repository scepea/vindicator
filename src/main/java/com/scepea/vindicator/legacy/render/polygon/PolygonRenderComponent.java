package com.scepea.vindicator.legacy.render.polygon;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ShortArray;

/**
 * @deprecated We should be rendering polygonal objects as sprites and
 * using {@link com.scepea.vindicator.render.sprite.SpriteComponent} to display them instead.
 */
@Deprecated
public class PolygonRenderComponent implements Component {

    private final Polygon border;
    private final ShortArray indices;
    private final Triangle[] fill;

    private final Color borderColor;
    private final Color fillColor;

    public PolygonRenderComponent(Polygon border, ShortArray indices, Triangle[] fill, Color borderColor, Color fillColor) {
        this.border = border;
        this.indices = indices;
        this.fill = fill;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public Polygon getBorder() {
        return border;
    }

    public ShortArray getIndices() {
        return indices;
    }

    public Triangle[] getFill() {
        return fill;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }
}
