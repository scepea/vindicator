package vindicator.render.text;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.scepea.vindicator.gameCommand.Status;
import com.scepea.vindicator.gameCommand.command.GameCommand;

import javax.inject.Inject;

public class ToStringCommandFactory {

    @Inject
    public ToStringCommandFactory(){}

    public <T> ToStringCommand create(Entity owner, Class<T> componentClass){
        return new ToStringCommand(owner, componentClass);
    }

    public static class ToStringCommand<T extends Component> implements GameCommand {

        private final Entity owner;
        private final Class<T> componentClass;


        private ToStringCommand(Entity owner, Class<T> componentClass) {
            this.owner = owner;
            this.componentClass = componentClass;
        }

        @Override
        public Status execute(float deltaTime) {
            TextComponent textComponent = owner.getComponent(TextComponent.class);
            if (textComponent != null) {
                textComponent.setText(owner.getComponent(componentClass).toString());
                return Status.SUCCEEDED;
            } else {
                return Status.FAILED;
            }
        }
    }
}
