package vindicator.physics.contact;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.scepea.vindicator.gameCommand.ExecutionQueue;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

/**
 * Processes all entities which have made contact and which have a contact action by adding the action to the command execution queue.
 */
public class ContactProcessingSystem extends EntitySystem {

    private ImmutableArray<Entity> collidedEntities;

    private final ExecutionQueue executionQueue;

    @Inject
    public ContactProcessingSystem(
            ExecutionQueue executionQueue
    ){
        this.executionQueue = executionQueue;
        priority = SystemPriority.DECISIONS.ordinal();
    }

    @Override
    public void addedToEngine(Engine engine) {
        collidedEntities = engine.getEntitiesFor(Family.all(ContactComponent.class, ContactActionComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : collidedEntities){
            executionQueue.offer(entity.getComponent(ContactActionComponent.class).getCommand());
        }
    }

}
