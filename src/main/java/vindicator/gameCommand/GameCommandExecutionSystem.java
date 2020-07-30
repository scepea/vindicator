package vindicator.gameCommand;

import com.badlogic.ashley.core.EntitySystem;
import com.scepea.vindicator.gameCommand.command.GameCommand;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;

public class GameCommandExecutionSystem extends EntitySystem {

    private final ExecutionQueue executionQueue;

    @Inject
    public GameCommandExecutionSystem(ExecutionQueue executionQueue){
        this.executionQueue = executionQueue;
        priority = SystemPriority.ACTIONS.ordinal();
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
