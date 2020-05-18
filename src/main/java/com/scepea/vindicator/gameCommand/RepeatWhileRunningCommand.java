package com.scepea.vindicator.gameCommand;

import com.scepea.vindicator.gameCommand.command.GameCommand;

import javax.inject.Inject;

public class RepeatWhileRunningCommand implements GameCommand {

    private final ExecutionQueue executionQueue;
    private final GameCommand command;

    @Inject
    public RepeatWhileRunningCommand(ExecutionQueue executionQueue, GameCommand command) {
        this.executionQueue = executionQueue;
        this.command = command;
    }

    @Override
    public Status execute(float deltaTime) {
        Status status = command.execute(deltaTime);
        if (status == Status.RUNNING) {
            executionQueue.offer(this);
        }
        return status;
    }

}
