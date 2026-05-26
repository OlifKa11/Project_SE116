package com.objectville.cells;

// Base class for all city buildings that provide something
public abstract class Facility extends Cell {
    public Facility(int x, int y, char symbol) {
        super(x, y, symbol);
    }
}