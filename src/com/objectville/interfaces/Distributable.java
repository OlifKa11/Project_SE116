package com.objectville.interfaces;

public interface Distributable {
    /**
     * Triggers the distribution logic (BFS for utilities or Radius for services)
     * to provide resources to eligible zones.
     */
    void distribute(Cell[][] map);
}
