package vindicator.render.text;

import com.badlogic.ashley.core.Component;

public class TextComponent implements Component {

    private String text;

    public TextComponent() {
        this("");
    }

    public TextComponent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
