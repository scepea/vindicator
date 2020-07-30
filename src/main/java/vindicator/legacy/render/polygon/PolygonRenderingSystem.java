package vindicator.legacy.render.polygon;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ShortArray;
import com.scepea.vindicator.transform.Transform;
import com.scepea.vindicator.transform.TransformEntityService;
import com.scepea.vindicator.transform.component.TransformComponent;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @deprecated We should be rendering polygonal objects as sprites and
 * using {@link com.scepea.vindicator.render.sprite.SpriteRenderingSystem} to display them instead.
 */
@Deprecated
public class PolygonRenderingSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private final Provider<ShapeRenderer> shapeRenderer;
    private final TransformEntityService transformEntityService;
    private final OrthographicCamera orthographicCamera;

    @Inject
    public PolygonRenderingSystem(
            Provider<ShapeRenderer> shapeRenderer,
            TransformEntityService transformEntityService,
            OrthographicCamera orthographicCamera
    ) {
        this.shapeRenderer = shapeRenderer;
        this.transformEntityService = transformEntityService;
        this.orthographicCamera = orthographicCamera;
        priority = Integer.MAX_VALUE;
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PolygonRenderComponent.class, TransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity entity: entities){
            update(entity);
        }
    }

    private void update(Entity entity) {
        ShapeRenderer shapeRenderer = this.shapeRenderer.get();
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        PolygonRenderComponent polygonRenderComponent = entity.getComponent(PolygonRenderComponent.class);

        {
            final Transform transform = transformEntityService.calculateGlobalTransform(entity);
            polygonRenderComponent.getBorder().setPosition(transform.getPosition().x, transform.getPosition().y);
            polygonRenderComponent.getBorder().setRotation(transform.getRotation() * MathUtils.radiansToDegrees);
        }

        float[] vertices = polygonRenderComponent.getBorder().getTransformedVertices();
        ShortArray indices = polygonRenderComponent.getIndices();
            /*
            For every ear within the border, calculate the triangles within.
             */
        for(int i = 0, j = 0; i < indices.size; i+=3, j++){
            polygonRenderComponent.getFill()[j] = new Triangle(new float[] {
                    vertices[indices.get(i) * 2],
                    vertices[indices.get(i) * 2 + 1],

                    vertices[indices.get(i + 1) * 2],
                    vertices[indices.get(i + 1) * 2 + 1],

                    vertices[indices.get(i + 2) * 2],
                    vertices[indices.get(i + 2) * 2 + 1]
            });
        }

        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(polygonRenderComponent.getFillColor());

        for (Triangle triangle : polygonRenderComponent.getFill()) {
            shapeRenderer.triangle(
                    triangle.getVertices()[0],
                    triangle.getVertices()[1],
                    triangle.getVertices()[2],
                    triangle.getVertices()[3],
                    triangle.getVertices()[4],
                    triangle.getVertices()[5]
            );
        }

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(polygonRenderComponent.getBorderColor());


        shapeRenderer.polygon(polygonRenderComponent.getBorder().getTransformedVertices());
        shapeRenderer.end();
    }

}
