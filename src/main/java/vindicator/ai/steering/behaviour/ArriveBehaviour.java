package vindicator.ai.steering.behaviour;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.LookWhereYouAreGoing;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;

public class ArriveBehaviour<T extends Vector<T>> extends SteeringBehavior<T> {

    private final SteeringAcceleration<T> tempSteeringAcceleration;
    private final Arrive<T> arrive;
    private final Face<T> face;

    public ArriveBehaviour(Steerable<T> owner, Steerable<T> target) {
        this(owner, target, 1.0F);
    }

    /*
    This behaviour is a very simple AI which creates a linear acceleration toward the target while rotating independently to face it.
    TODO a more sophisticated behaviour which looks where its target will be at a prediction time.
     */
    public ArriveBehaviour(Steerable<T> owner, Steerable<T> target, float maxPredictionTime) {
        super(owner);

        tempSteeringAcceleration = new SteeringAcceleration<>(owner.getPosition().cpy().setZero());

        float linearDecelerationRadius = (owner.getMaxLinearSpeed() * owner.getMaxLinearSpeed()) / (2 * owner.getMaxLinearAcceleration());

        arrive = new Arrive<>(owner, target);
        arrive.setDecelerationRadius(linearDecelerationRadius);
        arrive.setArrivalTolerance(linearDecelerationRadius * 1/100f);

        float angularDecelerationRadius = (owner.getMaxAngularSpeed() * owner.getMaxAngularSpeed()) / (2 * owner.getMaxAngularAcceleration());

        LookWhereYouAreGoing<T> lookWhereYouAreGoing = new LookWhereYouAreGoing<>(owner);
        lookWhereYouAreGoing.setDecelerationRadius(angularDecelerationRadius);
        lookWhereYouAreGoing.setAlignTolerance(0.1f * MathUtils.degreesToRadians);

        face = new Face<>(owner, target);
        face.setDecelerationRadius(angularDecelerationRadius);
        face.setAlignTolerance(0.1f * MathUtils.degreesToRadians);
    }


    @Override
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();

        arrive.calculateSteering(tempSteeringAcceleration);

        steeringAcceleration.add(tempSteeringAcceleration);

        face.calculateSteering(tempSteeringAcceleration);
        steeringAcceleration.add(tempSteeringAcceleration);

        return steeringAcceleration;
    }

    private T project(T a, T b){
        return b.scl(a.dot(b));
        }
}
