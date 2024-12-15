package org.example;

import java.util.*;

public class PriorityCargoQueue {
    private PriorityQueue<Cargo> queue;

    public PriorityCargoQueue() {
        this.queue = new PriorityQueue<>(new Comparator<Cargo>() {
            @Override
            public int compare(Cargo c1, Cargo c2) {
                return Integer.compare(c1.getDeliveryTime(), c2.getDeliveryTime());
            }
        });
    }

    public void addCargo(Cargo cargo) {
        queue.add(cargo);
    }

    public Cargo processCargo() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void printQueue() {
        for (Cargo cargo : queue) {
            System.out.println(cargo);
        }
    }
}
