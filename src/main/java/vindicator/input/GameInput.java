package vindicator.input;

import javax.inject.Singleton;

@Singleton
public interface GameInput {

    /**
     *
     * @return True if the game input needs to be reevaluated in the next engine update.
     */
    void evaluate();

    void resolve();

}
