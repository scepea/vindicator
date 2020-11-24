package com.scepea.vindicator.physics.contact;

import com.scepea.vindicator.job.JobComponent;
import com.scepea.vindicator.job.api.EntityJob;

/**
 * Represents an action which should happen when an entity collides with another.
 */
public class ContactActionComponent extends JobComponent {

    public ContactActionComponent(EntityJob contactCommand) {
        super(contactCommand);
    }

}
