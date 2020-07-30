package vindicator.ai.behaviourTree;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;


public class BehaviourTreeSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Inject
    public BehaviourTreeSystem() {
        priority = SystemPriority.DECISIONS.ordinal();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BehaviourTreeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            entity.getComponent(BehaviourTreeComponent.class).getBehaviorTree().step();
        }
    }

}
