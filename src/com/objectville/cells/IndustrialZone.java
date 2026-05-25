package com.objectville.cells;

import com.objectville.resources.CityResources;

public class IndustrialZone extends Zone {

    public IndustrialZone(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

        if (isOperational()) {

            if (level < 10) {
                level++;
                System.out.println("Industrial zone at (" + x + "," + y + ") leveled up to " + level);
            }

        } else {

            if (level > 1) {
                level--;
                System.out.println("Industrial zone at (" + x + "," + y + ") leveled down to " + level);
            }
        }

        produce();
    }

    @Override
    public void produce() {

        if (isOperational()) {

            populationOrJobs += level * 3;

            CityResources.totalGoods += level * 5;

            System.out.println("Industrial zone at (" + x + "," + y +
                    ") produced goods.");
        }
    }
}
