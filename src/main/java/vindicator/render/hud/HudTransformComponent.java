package vindicator.render.hud;

import com.badlogic.ashley.core.Component;
import com.scepea.vindicator.transform.Transform;

public class HudTransformComponent implements Component {
    private final Transform transform;

    public HudTransformComponent(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

}
