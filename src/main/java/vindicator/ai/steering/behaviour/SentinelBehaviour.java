package vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;

public class SentinelBehaviour <T extends Vector<T>> extends SteeringBehavior<T> {

    private final Face<T> face;

    public SentinelBehaviour(Steerable<T> owner, T target){
        super(owner);

        float angularDecelerationRadius = (owner.getMaxAngularSpeed() * owner.getMaxAngularSpeed()) / (2 * owner.getMaxAngularAcceleration());

        // TODO Create new behaviour which brings owner to halt ASAP.

        face = new Face<T>(owner, new SteerableAdapter<T>() {
            @Override
            public T getPosition(){
                return target;
            }
        });
        face.setDecelerationRadius(angularDecelerationRadius);
        face.setAlignTolerance(0.1f * MathUtils.degreesToRadians);
    }

    @Override
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();
        face.calculateSteering(steeringAcceleration);
        return steeringAcceleration;
    }
}
