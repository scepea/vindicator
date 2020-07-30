package vindicator.physics.contact;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

/**
 * Removes all contact data after they have been processed.
 */
public class ContactCleanupSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Inject
    public ContactCleanupSystem(){
        priority = SystemPriority.CLEANUP.ordinal();
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(ContactComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity entity: entities) {
            entity.remove(ContactComponent.class);
        }
    }
}
