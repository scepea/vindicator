package vindicator.physics;

/**
 * Simple service to provide the world scale in pixels per unit.
 */
public class WorldScale {

    private final float pixelsPerUnit;

    public WorldScale(float pixelsPerUnit) {
        this.pixelsPerUnit = pixelsPerUnit;
    }

    public float getPixelsPerUnit() {
        return pixelsPerUnit;
    }

}
