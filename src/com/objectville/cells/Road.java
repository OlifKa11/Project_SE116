package com.objectville.cells;

import com.objectville.interfaces.IPassable;

// Roads transfer resources and utilities
public class Road extends Cell implements IPassable {
    public Road(int x, int y) {
        super(x, y, 'R');
    }

    @Override
    public boolean canPassUtility() {
        return true; // utilities can pass through roads
    }
}