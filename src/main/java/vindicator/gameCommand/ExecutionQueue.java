package vindicator.gameCommand;

import com.scepea.vindicator.gameCommand.command.GameCommand;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Singleton
public class ExecutionQueue {

    private static final Queue<GameCommand> gameCommands = new LinkedList<>();
    private static final Set<GameCommand> gameCommandSet = new HashSet<>();

    @Inject public ExecutionQueue() {}

    public GameCommand poll() {
        GameCommand gameCommand = gameCommands.poll();
        gameCommandSet.remove(gameCommand);
        return gameCommand;

    }

    public void offer(GameCommand gameCommand){
        if (!gameCommandSet.contains(gameCommand)) {
            gameCommands.offer(gameCommand);
            gameCommandSet.add(gameCommand);
        }
    }

    public void remove(GameCommand gameCommand){
        gameCommands.remove(gameCommand);
        gameCommandSet.remove(gameCommand);
    }

    public int size(){
        return gameCommands.size();
    }
}
