package com.scepea.vindicator.gameCommand;

import com.badlogic.ashley.core.EntitySystem;
import com.scepea.vindicator.gameCommand.command.GameCommand;

import javax.inject.Inject;

public class GameCommandExecutionSystem extends EntitySystem {

    private final ExecutionQueue executionQueue;

    @Inject
    public GameCommandExecutionSystem(ExecutionQueue executionQueue){
        this.executionQueue = executionQueue;
        priority = Integer.MIN_VALUE + 2;
    }

    @Override
    public void update(float deltaTime) {
        int iterations = executionQueue.size();
        GameCommand gameCommand;
        for (int i = 0; i < iterations ; i++){
            gameCommand = executionQueue.poll();
            gameCommand.execute(deltaTime);
        }
    }
}
