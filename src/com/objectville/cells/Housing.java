package com.objectville.cells;

public class Housing extends Zone{
   private int consumedLifestyle;

    public Housing(int x, int y) {
        super(x, y, 'H');
        this.consumedLifestyle = 0;
    }
    public void consumeLifestyle(int amount) {
        this.consumedLifestyle = amount;
    }

    @Override
    public void updateLevel() {

        
        if (electirictyReceived == 0 ||
                waterReceived == 0 ||
                internetReceived == 0) {

            level = 0;
            return;
        }

        
        if (hasSecurity &&
                hasHealth &&
                hasEducation &&
                consumedLifestyle > 0) {

            if (level < 3) {
                level++;
            }
        }

        
        else if (hasSecurity &&
                hasHealth &&
                hasEducation) {

            if (level > 2) {
                level--;
            }

            else if (level < 2) {
                level++;
            }
        }

        
        else {

            if (level > 1) {
                level--;
            }

            else if (level < 1) {
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

            default:
                currentOutput = 2 * m + consumedLifestyle;
                break;
        }
    }
    public void resetTickData() {
        consumedLifestyle = 0;
    }
}
