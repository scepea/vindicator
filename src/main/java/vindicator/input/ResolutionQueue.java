package vindicator.input;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@Singleton
public class ResolutionQueue {
    private final Queue<GameInput> queue = new LinkedList<>();

    @Inject public ResolutionQueue() {}

    public Optional<GameInput> poll(){
        return Optional.ofNullable(queue.poll());
    }

    public void offer(GameInput gameInput){
        queue.offer(gameInput);
    }
}
