package com.objectville.cells;

public class Industrial extends Zone{
  private int receivedPopulation;

    public Industrial(int x, int y) {
        super(x, y, 'I');
        this.receivedPopulation = 0;
    }
    public void receivePopulation(int amount) {
        this.receivedPopulation = amount;
    }

    @Override
    public void updateLevel() {

        
        if (electricityReceived == 0 ||
                waterReceived == 0 ||
                receivedPopulation == 0) {

            level = 0;
            return;
        }

        int targetLevel = 1;

        if (hasSecurity) {
            targetLevel = 2;
        }

        if (hasSecurity && receivedPopulation > 1) {
            targetLevel = 3;
        }

        if (level < targetLevel) {
            level++;
        } else if (level > targetLevel) {
            level--;
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
                currentOutput = 2 * m + receivedPopulation;
                break;
        }
    }
    public void resetTickValues() {
       super.resetTickValues();
       receivedPopulation = 0;
    }
}
