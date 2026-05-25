package com.objectville.cells;

import com.objectville.resources.CityResources;

public class IndustrialZone extends Zone {

    public IndustrialZone(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

        // LEVEL 1
        if (populationOrJobs > 0 && isPowered && isWatered) {

            if (level < 1) {
                level = 1;

                System.out.println(
                        "Industrial zone at (" + x + "," + y +
                                ") leveled up to " + level
                );
            }

            // LEVEL 2
            if (hasSecurity) {

                if (level < 2) {
                    level = 2;

                    System.out.println(
                            "Industrial zone at (" + x + "," + y +
                                    ") leveled up to " + level
                    );
                }

                // LEVEL 3
                if (populationOrJobs > 5) {

                    if (level < 3) {
                        level = 3;

                        System.out.println(
                                "Industrial zone at (" + x + "," + y +
                                        ") leveled up to " + level
                        );
                    }
                }
            }

        } else {
            level = 0;

            System.out.println(
                    "Industrial zone at (" + x + "," + y +
                            ") dropped to level 0"
            );
        }

        produce();
    }

    @Override
    public void produce() {
        if (isOperational()) {
            int goodsProduced = level * 10;

            CityResources.addGoods(goodsProduced);

            System.out.println(
                    "Industrial zone at (" + x + "," + y +
                            ") produced " + goodsProduced + " goods."
            );
        }

    }
    @Override
    public boolean isOperational() {
        return isPowered && isInternetConnected;
    }
}
