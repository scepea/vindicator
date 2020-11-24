package com.scepea.vindicator.render.text;

import com.badlogic.ashley.core.Component;

public class TextComponent implements Component {

    private final Object ref;

    public TextComponent(Object ref) {
        this.ref = ref;
    }

    @Override
    public String toString(){
        return ref.toString();
    }
}
