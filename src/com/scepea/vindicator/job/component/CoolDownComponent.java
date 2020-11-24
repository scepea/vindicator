package com.scepea.vindicator.job.component;

import com.badlogic.ashley.core.Component;

public class CoolDownComponent implements Component {
    private float coolDown;

    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    public void reduceCoolDown(float reduction){
        coolDown -= reduction;
    }

}
