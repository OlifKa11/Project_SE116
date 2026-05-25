package com.objectville.cells;

public class Industrial extends Zone {

    private int consumedPopulation;

    public Industrial(int x, int y) {
        super(x, y, 'I');
        this.consumedPopulation = 0;
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public void calculateOutput() {

    }
}
