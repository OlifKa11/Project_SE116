package com.objectville.interfaces;

import com.objectville.cells.Cell;

public interface Distributable {
    /**
     * Executes the distribution logic across the simulation grid.
     * @param map The current 2D grid representing the simulation state.
     */
    void distribute(Cell[][] map);
}