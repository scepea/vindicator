package vindicator.input;

import com.badlogic.gdx.InputAdapter;
import com.scepea.vindicator.input.binding.ButtonGameInputBindings;
import com.scepea.vindicator.input.binding.KeyGameInputBindings;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class GameInputProcessor extends InputAdapter {

    private final KeyGameInputBindings keyGameInputBindings;
    private final ButtonGameInputBindings buttonGameInputBindings;
    private final EvaluationQueue evaluationQueue;
    private final ResolutionQueue resolutionQueue;

    private final Map<Integer, GameInput> keyInputMap;
    private final Map<Integer, GameInput> buttonInputMap;

    private final TouchDragged touchDragged;
    private final MouseMoved mouseMoved;

    @Inject
    public GameInputProcessor(
            KeyGameInputBindings keyGameInputBindings,
            ButtonGameInputBindings buttonGameInputBindings,
            EvaluationQueue evaluationQueue,
            ResolutionQueue resolutionQueue,
            TouchDragged touchDragged,
            MouseMoved mouseMoved
    ){
        this.keyGameInputBindings = keyGameInputBindings;
        this.buttonGameInputBindings = buttonGameInputBindings;
        this.evaluationQueue = evaluationQueue;
        this.resolutionQueue = resolutionQueue;
        this.touchDragged = touchDragged;
        this.mouseMoved = mouseMoved;
        keyInputMap = new HashMap<>();
        buttonInputMap = new HashMap<>();
    }

    @Override
    public boolean keyDown (int keycode) {
        Optional<GameInput> inputType = keyGameInputBindings.getInputForKey(keycode);
        if(inputType.isPresent()){
            processGameInput(keycode, inputType.get(), keyInputMap);
            return true;
        } else {
            return false;
        }
    }

    private void processGameInput(int keycode, GameInput inputForKey, Map<Integer, GameInput> integerInputMap) {
            evaluationQueue.offer(inputForKey);
            integerInputMap.put(keycode, inputForKey);
    }

    @Override
    public boolean keyUp (int keycode) {
        resolutionQueue.offer(keyInputMap.get(keycode));
        keyInputMap.remove(keycode);

        return true;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        Optional<GameInput> inputType = buttonGameInputBindings.getInputForKey(button);
        if(inputType.isPresent()){
            processGameInput(button, inputType.get(), buttonInputMap);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        resolutionQueue.offer(buttonInputMap.get(button));
        buttonInputMap.remove(button);
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer){
        evaluationQueue.offer(touchDragged);
        return true;
    }

    @Override
     public boolean mouseMoved (int screenX, int screenY){
        evaluationQueue.offer(mouseMoved);
        return true;
    }

}
