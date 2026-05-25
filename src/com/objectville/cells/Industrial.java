package com.objectville.cells;

public class Industrial extends Zone {

    private int consumedPopulation;

    public Industrial(int x, int y) {
        super(x, y, 'I');
        this.consumedPopulation = 0;
    }

    @Override
    public void updateLevel() {

        // Missing utilities -> immediate drop to level 0
        if (electirictyReceived == 0 ||
                waterReceived == 0) {

            level = 0;
            return;
        }

        // Level 3 conditions
        if (hasSecurity &&
                consumedPopulation > 1) {

            if (level < 3) {
                level++;
            }

            return;
        }

        // Level 2 conditions
        if (hasSecurity) {

            if (level < 2) {
                level++;
            }

            return;
        }

        // Level 1 conditions
        if (consumedPopulation > 0) {

            if (level < 1) {
                level++;
            }
        }
    }

    @Override
    public void calculateOutput() {

        int m = Math.min(
                electirictyReceived,
                waterReceived
        );

        switch (level) {

            case 0:
                currentOutput = 0;
                break;

            case 1:
                currentOutput = m;
                break;

            case 2:
                currentOutput = 2 * m;
                break;

            case 3:
                currentOutput = 2 * m + consumedPopulation;
                break;
        }
    }
}
