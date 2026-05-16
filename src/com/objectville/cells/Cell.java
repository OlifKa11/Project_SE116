package com.objectville.cells;

public abstract class Cell {
    // Every cell has a fixed coordinate on the map grid
    protected int x;
    protected int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters for subclasses to calculate distances or perform BFS
    public int getX() { return x; }
    public int getY() { return y; }
}