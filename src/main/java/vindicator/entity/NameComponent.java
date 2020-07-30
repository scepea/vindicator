package vindicator.entity;

import com.badlogic.ashley.core.Component;

/**
 * Utility component for assigning a generic - i.e. non-specific name to an entity.
 */
public class NameComponent implements Component {

    private final String name;

    public NameComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
