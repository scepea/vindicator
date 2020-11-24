package com.scepea.vindicator.job.component;

import com.badlogic.ashley.core.Component;
import com.scepea.vindicator.job.api.EntityJob;
import com.scepea.vindicator.job.api.JobPriorityComparator;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ExecutionQueueComponent implements Component {

    private final PriorityQueue<EntityJob> entityJobs;
    private final Set<EntityJob> entityJobSet;

    public ExecutionQueueComponent() {
        entityJobs = new PriorityQueue<>(new JobPriorityComparator());
        entityJobSet = new HashSet<>();
    }

    public void offer(EntityJob entityJob){
        if (!entityJobSet.contains(entityJob)) {
            entityJobs.offer(entityJob);
            entityJobSet.add(entityJob);
        }
    }

    public void remove(EntityJob entityJob){
        entityJobs.remove(entityJob);
        entityJobSet.remove(entityJob);
    }

    /**
     * Returns a copy of the entity job priority queue before clearing it along with the entity job set.
     * @return
     */
    public PriorityQueue<EntityJob> flush(){
        PriorityQueue<EntityJob> flush = new PriorityQueue<>(entityJobs);
        entityJobs.clear();
        entityJobSet.clear();
        return flush;
    }
}
