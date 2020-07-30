package vindicator.gameCommand;

import com.badlogic.ashley.core.Component;
import com.scepea.vindicator.gameCommand.command.GameCommand;

public abstract class CommandComponent implements Component {

    private final GameCommand command;

    public CommandComponent(GameCommand command) {
        this.command = command;
    }

    public GameCommand getCommand() {
        return command;
    }

}
