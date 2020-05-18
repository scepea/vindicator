package com.scepea.vindicator.entity;

import com.badlogic.ashley.core.Component;

public class NameComponent implements Component {

    private final String name;

    public NameComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
