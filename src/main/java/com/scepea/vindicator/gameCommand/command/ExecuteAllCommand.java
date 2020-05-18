package com.scepea.vindicator.gameCommand.command;

import com.scepea.vindicator.gameCommand.Status;

import java.util.Collection;

public class ExecuteAllCommand implements GameCommand {

    private Collection<GameCommand> gameCommands;

    public ExecuteAllCommand(Collection<GameCommand> gameCommands) {
        this.gameCommands = gameCommands;
    }

    @Override
    public Status execute(float deltaTime) {
        Status returnStatus = Status.SUCCEEDED;
        for (GameCommand command : gameCommands){
            if (command.execute(deltaTime) == Status.FAILED) returnStatus = Status.FAILED;
        }
        return returnStatus;
    }

}
