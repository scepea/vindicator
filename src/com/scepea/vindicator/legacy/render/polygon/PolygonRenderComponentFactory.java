package com.scepea.vindicator.legacy.render.polygon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ShortArray;
import com.scepea.vindicator.render.sprite.SpriteComponentBuilder;

import javax.inject.Inject;

/**
 * @deprecated We should be rendering polygonal objects as sprites and
 * using {@link SpriteComponentBuilder} to display them instead.
 */
@Deprecated
public class PolygonRenderComponentFactory {

    private final EarClippingTriangulator earClippingTriangulator;

    @Inject
    public PolygonRenderComponentFactory(EarClippingTriangulator earClippingTriangulator){
        this.earClippingTriangulator = earClippingTriangulator;
    }

    public PolygonRenderComponent create(Polygon border, Color borderColor, Color fillColor){
        ShortArray indices = earClippingTriangulator.computeTriangles(border.getVertices());

        return new PolygonRenderComponent(
                border,
                new ShortArray(indices),
                new Triangle[indices.size / 3],
                borderColor,
                fillColor
        );

    }

}
