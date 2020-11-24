package com.scepea.vindicator.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.scepea.vindicator.input.binding.ButtonGameInputBindings;
import com.scepea.vindicator.input.binding.KeyGameInputBindings;
import com.scepea.vindicator.input.binding.ScrollInputBindings;
import com.scepea.vindicator.job.JobComponent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class GameInputProcessor extends InputAdapter {

    private final Engine engine;

    private final KeyGameInputBindings keyGameInputBindings;
    private final ButtonGameInputBindings buttonGameInputBindings;
    private final ScrollInputBindings scrollInputBindings;

    ImmutableArray<Entity> entities;

    @Inject
    public GameInputProcessor(
            Engine engine,
            KeyGameInputBindings keyGameInputBindings,
            ButtonGameInputBindings buttonGameInputBindings,
            ScrollInputBindings scrollInputBindings
    ){
        this.engine = engine;
        this.keyGameInputBindings = keyGameInputBindings;
        this.buttonGameInputBindings = buttonGameInputBindings;
        this.scrollInputBindings = scrollInputBindings;
    }

    @Override
    public boolean keyDown (int keycode) {
        return schedule(keycode, keyGameInputBindings);
    }

    @Override
    public boolean keyUp (int keycode) {
        return cancel(keycode, keyGameInputBindings);
    }


    private boolean schedule(int keycode, IntegerGameInputBindings keyInputMap) {
        Optional<Class<? extends JobComponent>> inputForKey = keyInputMap.getInputForKey(keycode);
        if (inputForKey.isPresent()) {
            entities = engine.getEntitiesFor(Family.all(inputForKey.get()).get());
            for (Entity entity : entities) {
                entity.getComponent(inputForKey.get()).getCommand().schedule();
            }
        }
        return true;
    }

    private boolean cancel(int keycode, IntegerGameInputBindings keyInputMap) {
        Optional<Class<? extends JobComponent>> inputForKey = keyInputMap.getInputForKey(keycode);
        if (inputForKey.isPresent()) {
            entities = engine.getEntitiesFor(Family.all(inputForKey.get()).get());
            for (Entity entity : entities) {
                entity.getComponent(inputForKey.get()).getCommand().cancel();
            }
        }
        return true;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return schedule(button, buttonGameInputBindings);
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return cancel(button, buttonGameInputBindings);
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {
        return schedule(amountY > 0 ? MathUtils.ceil(amountY) : MathUtils.floor(amountY), scrollInputBindings);
    }
}
