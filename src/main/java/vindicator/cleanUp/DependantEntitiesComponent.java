package vindicator.cleanUp;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.HashSet;
import java.util.Set;

public class DependantEntitiesComponent implements Component {

    private final Set<Entity> dependantEntities;

    public DependantEntitiesComponent() {
        this.dependantEntities = new HashSet<>();
    }

    public Set<Entity> getDependantEntities() {
        return dependantEntities;
    }

    public void addDependantEntity(Entity entity){
        dependantEntities.add(entity);
    }

}
