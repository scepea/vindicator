package vindicator.physics.contact;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * Represents a contact between an entity and another.
 */
public class ContactComponent implements Component {
    private final Entity entity;

    public ContactComponent(Entity collisionEntity) {
        this.entity = collisionEntity;
    }

    public Entity getEntity() {
        return entity;
    }

}
