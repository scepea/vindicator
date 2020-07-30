package vindicator.legacy.asteroid;

import javax.inject.Inject;

/**
 * @deprecated Was introduced when Forlorn was just an asteroids clone. Should be removed but has been useful for testing collisions.
 */
@Deprecated
public class AsteroidComponentFactory {

    @Inject
    public AsteroidComponentFactory(){}

    public AsteroidComponent createLargeAsteroidComponent(){
        return new AsteroidComponent(AsteroidComponent.AsteroidSize.LARGE);
    }

    public AsteroidComponent createMediumAsteroidComponent(){
        return new AsteroidComponent(AsteroidComponent.AsteroidSize.MEDIUM);
    }

    public AsteroidComponent createSmallAsteroidComponent(){
        return new AsteroidComponent(AsteroidComponent.AsteroidSize.SMALL);
    }
}
