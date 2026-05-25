package com.objectville.cells;

public class Housing extends Zone {

    private int consumedLifestyle;

    public Housing(int x, int y) {
        super(x, y, 'H');
        this.consumedLifestyle = 0;
    }

    @Override
    public void updateLevel() {

        // No utilities -> immediate drop to 0
        if (electirictyReceived == 0 ||
                waterReceived == 0 ||
                internetReceived == 0) {

            level = 0;
            return;
        }

        // Level 3 conditions
        if (hasSecurity &&
                hasHealth &&
                hasEducation &&
                consumedLifestyle > 0) {

            if (level < 3) {
                level++;
            }
        }

        // Level 2 conditions
        else if (hasSecurity &&
                hasHealth &&
                hasEducation) {

            if (level < 2) {
                level++;
            }
        }

        // Level 1 conditions
        else {

            if (level < 1) {
                level++;
            }
        }
    }

    @Override
    public void calculateOutput() {

        int m = Math.min(
                electirictyReceived,
                Math.min(waterReceived, internetReceived)
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
                currentOutput = 2 * m + consumedLifestyle;
                break;
        }
    }
}
