package vindicator.gameCommand.command;

import com.scepea.vindicator.gameCommand.ExecutionQueue;
import com.scepea.vindicator.gameCommand.Status;

public class RepeatCommand implements GameCommand {

    private final ExecutionQueue executionQueue;
    private final GameCommand command;

    public RepeatCommand(ExecutionQueue executionQueue, GameCommand command) {
        this.executionQueue = executionQueue;
        this.command = command;
    }

    @Override
    public Status execute(float deltaTime) {
        Status status = command.execute(deltaTime);
        if (status != Status.FAILED) {
            executionQueue.offer(this);
            return Status.RUNNING;
        } else {
            return status;
        }
    }


}
