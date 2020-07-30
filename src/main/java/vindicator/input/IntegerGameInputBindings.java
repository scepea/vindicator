package vindicator.input;

import java.util.*;

public abstract class IntegerGameInputBindings {

    private final Map<Integer, GameInput> integerGameInputMap;
    private final Map<GameInput, Set<Integer>> gameInputIntegerMap;

    public IntegerGameInputBindings(
    ) {
        integerGameInputMap = new HashMap<>();
        gameInputIntegerMap = new HashMap<>();
    }

    protected void addMapping(int key, GameInput gameInput){
        integerGameInputMap.put(key, gameInput);
        if (gameInputIntegerMap.containsKey(gameInput)){
            gameInputIntegerMap.get(gameInput).add(key);
        } else {
            HashSet<Integer> newSet = new HashSet<>();
            newSet.add(key);
            gameInputIntegerMap.put(gameInput, newSet);
        }
    }

    public Optional<GameInput> getInputForKey(int keycode){
        return Optional.ofNullable(integerGameInputMap.get(keycode));
    }

    public Optional<Set<Integer>> getKeysForGameInput(GameInput gameInput){
        return Optional.ofNullable(gameInputIntegerMap.get(gameInput));
    }


}
