package vindicator.transform;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.scepea.vindicator.entity.EntityService;
import com.scepea.vindicator.entity.OwnerComponent;
import com.scepea.vindicator.transform.component.TransformComponent;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class TransformEntityService {

    private final EntityService entityService;

    @Inject
    public TransformEntityService(EntityService entityService) {
        this.entityService = entityService;
    }

    public Transform calculateGlobalTransform(Entity entity){
        return calculateCompositeTransform(buildTransformList(entity));
    }

    public List<Transform> buildTransformList(Entity entity){
        List<Transform> transformList = new LinkedList<>();

        for (TransformComponent transformComponent : entityService.getAllMatchingComponentsInEntityBranch(entity, OwnerComponent.class, TransformComponent.class)){
            transformList.add(transformComponent.getTransform());
        }

        return transformList;
    }

    public Transform calculateCompositeTransform(List<Transform> transformList){
        Transform accumulator = new Transform(new Vector2(0,0), 0);
        if (transformList.size() > 0) {
            for (Transform transform : transformList){
                accumulator.getPosition().rotateRad(transform.getRotation());
                accumulator.getPosition().add(transform.getPosition());
                accumulator.setRotation(accumulator.getRotation() + transform.getRotation());
            }
        }
        return accumulator;
    }



}
