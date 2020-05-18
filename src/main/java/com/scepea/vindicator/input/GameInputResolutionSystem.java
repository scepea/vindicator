package com.scepea.vindicator.input;

import com.badlogic.ashley.core.EntitySystem;

import javax.inject.Inject;
import java.util.Optional;

public class GameInputResolutionSystem extends EntitySystem {

    private final ResolutionQueue resolutionQueue;

    @Inject
    public GameInputResolutionSystem(ResolutionQueue resolutionQueue){
        this.resolutionQueue = resolutionQueue;
        priority = Integer.MIN_VALUE + 3;
    }

    @Override
    public void update(float deltaTime) {
        Optional<GameInput> resolveGameInput = resolutionQueue.poll();
        while(resolveGameInput.isPresent()){
            resolveGameInput.get().resolve();
            resolveGameInput = resolutionQueue.poll();
        }

    }
}
