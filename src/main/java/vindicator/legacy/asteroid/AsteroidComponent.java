package vindicator.legacy.asteroid;

import com.badlogic.ashley.core.Component;

/**
 * @deprecated Was introduced when the game was just an asteroids clone. Should be removed but also be remembered for its service in testing collisions.
 */
@Deprecated
public class AsteroidComponent implements Component {

    public enum AsteroidSize{
        SMALL, MEDIUM, LARGE
    }

    private final AsteroidSize asteroidSize;

    public AsteroidComponent(AsteroidSize asteroidSize){
        this.asteroidSize = asteroidSize;
    }

    public AsteroidSize getAsteroidSize() {
        return asteroidSize;
    }
}
