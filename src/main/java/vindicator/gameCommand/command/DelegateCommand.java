package vindicator.gameCommand.command;

import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.entity.EntityComponent;
import com.scepea.vindicator.gameCommand.CommandComponent;
import com.scepea.vindicator.gameCommand.Status;

public class DelegateCommand<T extends EntityComponent, U extends CommandComponent> implements GameCommand {

    private final Entity owner;
    private final Class<T> relation;
    private final Class<U> action;

    public DelegateCommand(Entity owner, Class<T> relation, Class<U> action) {
        this.owner = owner;
        this.relation = relation;
        this.action = action;
    }

    @Override
    public Status execute(float deltaTime) {
        EntityComponent entityComponent = owner.getComponent(relation);
        if (entityComponent != null) {
            CommandComponent commandComponent = entityComponent.getEntity().getComponent(action);
            if (commandComponent != null) {
                return commandComponent.getCommand().execute(deltaTime);
            }
        }
        return Status.FAILED;
    }

}
