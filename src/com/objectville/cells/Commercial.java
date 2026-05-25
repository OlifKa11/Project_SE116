package com.objectville.cells;

public class Commercial extends Zone {

    private int consumedPopulation;
    private int consumedGoods;

    public Commercial(int x, int y) {
        super(x, y, 'C');
        this.consumedPopulation = 0;
        this.consumedGoods = 0;
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public void calculateOutput() {

    }
}
