package com.objectville.cells;

import com.objectville.interfaces.Updatable;

public class ResidentialZone extends Cell implements Updatable {

    private int level;

    public ResidentialZone(int x, int y) {
        super(x, y);
        this.level = 0;
    }

    @Override
    public void update() {
        System.out.println("Residential zone updated.");
    }
}
