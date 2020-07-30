package vindicator.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * Generic component for a single entity relationship.
 */
public abstract class EntityComponent implements Component {

    private final Entity entity;

    public EntityComponent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
