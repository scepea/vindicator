package vindicator.entity;

import com.badlogic.ashley.core.Entity;


/**
 * Generic ownership component. Extremely useful for building composite entities - entities made up from one or many sub entities which existing holistically.
 * Should the root entity no longer exist it should be assumed that all of the sub entities should be recursively removed.
 * More specific ownership relationships may be provided but it is assumed that many relationships will navigate the same ownership tree.
 */
public class OwnerComponent extends EntityComponent {

    public OwnerComponent(Entity entity) {
        super(entity);
    }

}
