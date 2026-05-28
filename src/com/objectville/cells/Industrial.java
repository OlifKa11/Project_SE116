package com.objectville.cells;

public class Industrial extends Zone{
  private int consumedPopulation;

    public Industrial(int x, int y) {
        super(x, y, 'I');
        this.consumedPopulation = 0;
    }
    public void consumePopulation(int amount) {
        this.consumedPopulation = amount;
    }

    @Override
    public void updateLevel() {

        
        if (electricityReceived == 0 ||
                waterReceived == 0 ||
                internetReceived == 0) {

            level = 0;
            return;
        }

        
        if (hasSecurity &&
                hasHealth &&
                hasEducation &&
                consumedPopulation > 0) {

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
                electricityReceived,
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

            default:
                currentOutput = 2 * m + consumedPopulation;
                break;
        }
    }
    public void resetTickData() {
        consumedPopulation = 0;
    }
}
