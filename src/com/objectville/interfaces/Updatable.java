package com.objectville.interfaces;

public interface Updatable {
    /**
     * Updates the state, level, and production values of the cell
     * based on the resources received during the current tick.
     */
    void update();
}
