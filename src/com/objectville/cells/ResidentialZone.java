package com.objectville.cells;

public class ResidentialZone extends Zone {

    public ResidentialZone(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

        if (isOperational()) {

            if (level < 10) {
                level++;
                System.out.println("Residential zone at (" + x + "," + y + ") leveled up to " + level);
            }

        } else {

            if (level > 1) {
                level--;
                System.out.println("Residential zone at (" + x + "," + y + ") leveled down to " + level);
            }
        }

        produce();
    }

    @Override
    public void produce() {

        populationOrJobs = level * 10;

        System.out.println(
                "Residential zone at (" + x + "," + y + ") produced population: "
                        + populationOrJobs
        );
    }
}
