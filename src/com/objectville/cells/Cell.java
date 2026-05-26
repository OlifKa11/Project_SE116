package com.objectville.cells;

// Base abstract class for all map entities
public abstract class Cell {
    protected int x;
    protected int y;
    protected char symbol;

    public Cell(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public char getSymbol() { return symbol; }
}