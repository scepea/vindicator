package vindicator.physics.contact;

import com.scepea.vindicator.gameCommand.CommandComponent;
import com.scepea.vindicator.gameCommand.command.GameCommand;

/**
 * Represents an action which should happen when an entity collides with another.
 */
public class ContactActionComponent extends CommandComponent {

    public ContactActionComponent(GameCommand contactCommand) {
        super(contactCommand);
    }

}
