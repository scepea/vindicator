package vindicator.gameCommand.command;

import com.scepea.vindicator.gameCommand.Status;

public interface GameCommand {

    /**
     *
     * @param deltaTime
     * @return True if the game command needs to be reevaluated in the next engine update.
     */
    Status execute(float deltaTime);
}
