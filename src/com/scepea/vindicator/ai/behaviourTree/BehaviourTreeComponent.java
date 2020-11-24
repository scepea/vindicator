package com.scepea.vindicator.ai.behaviourTree;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.btree.BehaviorTree;

public class BehaviourTreeComponent<T> implements Component {
    private final BehaviorTree<T> behaviorTree;

    public BehaviourTreeComponent(BehaviorTree<T> behaviorTree) {
        this.behaviorTree = behaviorTree;
    }

    public BehaviorTree<T> getBehaviorTree() {
        return behaviorTree;
    }
}
