package vindicator.render.preRender;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

public class PreRenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Inject
    public PreRenderSystem() {
        priority = SystemPriority.PRE_RENDER.ordinal();
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PreRenderComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity entity : entities) {
            entity.getComponent(PreRenderComponent.class).getCommand().execute(deltaTime);
        }
    }

}
