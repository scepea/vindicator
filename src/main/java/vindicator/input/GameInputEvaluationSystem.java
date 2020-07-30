package vindicator.input;

import com.badlogic.ashley.core.EntitySystem;
import com.scepea.vindicator.utils.SystemPriority;

import javax.inject.Inject;
import java.util.Optional;

public class GameInputEvaluationSystem extends EntitySystem {

    private final EvaluationQueue evaluationQueue;

    @Inject
    public GameInputEvaluationSystem(
            EvaluationQueue evaluationQueue
    ){
        this.evaluationQueue = evaluationQueue;
        priority = SystemPriority.POLLING.ordinal();
    }

    @Override
    public void update(float deltaTime) {
        Optional<GameInput> evaluateGameInput = evaluationQueue.poll();

        while(evaluateGameInput.isPresent()){
            evaluateGameInput.get().evaluate();
            evaluateGameInput = evaluationQueue.poll();
        }

    }

}
