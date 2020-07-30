package vindicator.physics.box2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.scepea.vindicator.physics.WorldScale;
import com.scepea.vindicator.physics.box2d.physicsBodyEditor.BodyEditorLoader;

import javax.inject.Inject;
import javax.inject.Provider;

public class Box2dPhysicsComponentBuilder {

    private final World world;
    private final WorldScale worldScale;

    private BodyType bodyType = BodyType.StaticBody; // Matches BodyDef#type
    private float linearDampening = 0; // Matches BodyDef#linearDampening
    private float angularDamping = 0; // Matches BodyDef#angularDamping

    private float density = 0; // Matches FixtureDef#density

    private boolean isBullet;

    private final Provider<BodyEditorLoader> bodyEditorLoader;

    /** The collision category bits. Normally you would just set one bit. */
    public short categoryBits = 0x0001;

    /** The collision mask bits. This states the categories that this shape would accept for collision. */
    public short maskBits = -1;

    /** Collision groups allow a certain group of objects to never collide (negative) or always collide (positive). Zero means no
     * collision group. Non-zero group filtering always wins against the mask bits. */
    public short groupIndex = 0;

    @Inject
    public Box2dPhysicsComponentBuilder(World world, WorldScale worldScale, Provider<BodyEditorLoader> bodyEditorLoader){
        this.world = world;
        this.worldScale = worldScale;
        this.bodyEditorLoader = bodyEditorLoader;
    }

    public Box2dPhysicsComponentBuilder withBodyType(BodyType bodyType){
        this.bodyType = bodyType;
        return this;
    }

    public Box2dPhysicsComponentBuilder withDensity(float density){
        this.density = density;
        return this;
    }

    public Box2dPhysicsComponentBuilder withLinearDampening(float linearDampening){
        this.linearDampening = linearDampening;
        return this;
    }

    public Box2dPhysicsComponentBuilder withAngularDampening(float angularDampening){
        this.angularDamping = angularDampening;
        return this;
    }

    public Box2dPhysicsComponentBuilder withBullet(boolean isBullet){
        this.isBullet = isBullet;
        return this;
    }

    public Box2dPhysicsComponentBuilder withCategoryBits(short categoryBits){
        this.categoryBits = categoryBits;
        return this;
    }

    public Box2dPhysicsComponentBuilder withMaskBits(short maskBits){
        this.maskBits = maskBits;
        return this;
    }

    public Box2dPhysicsComponentBuilder withGroupIndex(short groupIndex){
        this.groupIndex = groupIndex;
        return this;
    }

    /**
     *
     * @param entity
     * @param name
     * @param length Length of the body in pixels based on its longest axis.
     * @return
     */
    public Box2dPhysicsComponent build(Entity entity, String name, int length){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.linearDamping = linearDampening;
        bodyDef.angularDamping = angularDamping;

        Body body = world.createBody(bodyDef);
        body.setUserData(entity);
        body.setBullet(isBullet);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.filter.categoryBits = this.categoryBits;
        fixtureDef.filter.maskBits = this.maskBits;
        fixtureDef.filter.groupIndex = this.groupIndex;


        bodyEditorLoader.get().attachFixture(body, name, fixtureDef, (float)length/worldScale.getPixelsPerUnit());

        return new Box2dPhysicsComponent(body);
    }



}
