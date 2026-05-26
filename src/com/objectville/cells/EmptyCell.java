package com.objectville.cells;

import com.objectville.interfaces.IPassable;

// Empty space acts like a wall for utilities
public class EmptyCell extends Cell implements IPassable {
    public EmptyCell(int x, int y) {
        super(x, y, 'E');
    }

    @Override
    public boolean canPassUtility() {
        return false; // Blocks propagation
    }
}