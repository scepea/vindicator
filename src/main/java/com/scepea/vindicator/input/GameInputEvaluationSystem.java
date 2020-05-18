package com.scepea.vindicator.input;

import com.badlogic.ashley.core.EntitySystem;

import javax.inject.Inject;
import java.util.Optional;

public class GameInputEvaluationSystem extends EntitySystem {

    private final EvaluationQueue evaluationQueue;

    @Inject
    public GameInputEvaluationSystem(
            EvaluationQueue evaluationQueue
    ){
        this.evaluationQueue = evaluationQueue;
        priority = Integer.MIN_VALUE + 1;
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
