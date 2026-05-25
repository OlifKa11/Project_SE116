package com.objectville.cells;
import com.objectville.resources.CityResources;
public class CommercialZone extends Zone {

    public CommercialZone(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

        if (isOperational()) {

            if (level < 10) {
                level++;
                System.out.println("Commercial zone at (" + x + "," + y + ") leveled up to " + level);
            }

        } else {

            if (level > 1) {
                level--;
                System.out.println("Commercial zone at (" + x + "," + y + ") leveled down to " + level);
            }
        }

        produce();
    }

    @Override
    public void produce() {
        if (isOperational()) {
            int lifestyleProduced = level * 7;

            CityResources.addLifestyle(lifestyleProduced);

            System.out.println(
                    "Commercial zone at (" + x + "," + y +
                            ") produced " + lifestyleProduced + " lifestyle."
            );
        }
    }
}
