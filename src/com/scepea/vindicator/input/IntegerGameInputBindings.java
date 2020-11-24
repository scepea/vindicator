package com.scepea.vindicator.input;

import com.scepea.vindicator.job.JobComponent;

import java.util.*;

public abstract class IntegerGameInputBindings {

    private final Map<Integer, Class<? extends JobComponent>> integerGameInputMap;
    private final Map<Class<? extends JobComponent>, Set<Integer>> gameInputIntegerMap;

    public IntegerGameInputBindings(
    ) {
        integerGameInputMap = new HashMap<>();
        gameInputIntegerMap = new HashMap<>();
    }

    protected void addMapping(int key, Class<? extends JobComponent> gameInput){
        integerGameInputMap.put(key, gameInput);
        if (gameInputIntegerMap.containsKey(gameInput)){
            gameInputIntegerMap.get(gameInput).add(key);
        } else {
            HashSet<Integer> newSet = new HashSet<>();
            newSet.add(key);
            gameInputIntegerMap.put(gameInput, newSet);
        }
    }

    public Optional<Class<? extends JobComponent>> getInputForKey(int keycode){
        return Optional.ofNullable(integerGameInputMap.get(keycode));
    }

    public Optional<Set<Integer>> getKeysForGameInput(Class<? extends JobComponent> gameInput){
        return Optional.ofNullable(gameInputIntegerMap.get(gameInput));
    }


}
