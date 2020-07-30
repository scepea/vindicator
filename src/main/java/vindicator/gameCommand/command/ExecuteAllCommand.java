package vindicator.gameCommand.command;

import com.scepea.vindicator.gameCommand.Status;

import java.util.Arrays;
import java.util.List;

public class ExecuteAllCommand implements GameCommand {

    private List<GameCommand> gameCommands;

    public ExecuteAllCommand(GameCommand... gameCommands) {
        this.gameCommands = Arrays.asList(gameCommands);
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
