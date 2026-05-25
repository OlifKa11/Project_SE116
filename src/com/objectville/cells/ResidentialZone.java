package com.objectville.cells;
import com.objectville.resources.CityResources;
public class ResidentialZone extends Zone {

    public ResidentialZone(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

        // LEVEL 1
        if (isPowered && isWatered && isInternetConnected) {

            if (level < 1) {
                level = 1;

                System.out.println(
                        "Residential zone at (" + x + "," + y +
                                ") leveled up to " + level
                );
            }

            // LEVEL 2
            if (hasSecurity && hasHealth && hasEducation) {

                if (level < 2) {
                    level = 2;

                    System.out.println(
                            "Residential zone at (" + x + "," + y +
                                    ") leveled up to " + level
                    );
                }

                // LEVEL 3
                if (lifestyleReceived > 0) {

                    if (level < 3) {
                        level = 3;

                        System.out.println(
                                "Residential zone at (" + x + "," + y +
                                        ") leveled up to " + level
                        );
                    }
                }
            }

        } else {
            level = 0;

            System.out.println(
                    "Residential zone at (" + x + "," + y +
                            ") dropped to level 0"
            );
        }

        produce();
    }

    @Override
    public void produce() {
        if (isOperational()) {
            int populationProduced = level * 5;

            CityResources.addPopulation(populationProduced);

            System.out.println(
                    "Residential zone at (" + x + "," + y +
                            ") added " + populationProduced + " population."
            );
        }
    }
}
