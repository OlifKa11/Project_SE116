package com.objectville.cells;

import com.objectville.interfaces.Updatable;

public class CommercialZone extends Cell implements Updatable {

    private int level;
    private int incomeGenerated;

    public CommercialZone(int x, int y) {
        super(x, y);
        this.level = 0;
        this.incomeGenerated = 0;
    }

    @Override
    public void update() {
        incomeGenerated = level * 3;
        System.out.println("Commercial zone updated.");
    }

    public int getIncomeGenerated() {
        return incomeGenerated;
    }
}
