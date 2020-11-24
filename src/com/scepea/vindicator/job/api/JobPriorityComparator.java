package com.scepea.vindicator.job.api;

import java.util.Comparator;

public class JobPriorityComparator implements Comparator<EntityJob> {

    @Override
    public int compare(EntityJob o1, EntityJob o2) {
        return o1.getJobPriority().ordinal() - o2.getJobPriority().ordinal();
    }

}
