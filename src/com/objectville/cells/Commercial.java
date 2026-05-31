package com.objectville.cells;

public class Commercial extends Zone {
  private int receivedPopulation;
    private int receivedGoods;

    public Commercial(int x, int y) {
        super(x, y, 'C');
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
    }

    public void receiveGoods(int amount) {
        this.receivedGoods = amount;
    }

    @Override
    public void updateLevel() {

        
        if (electricityReceived == 0 ||
                waterReceived == 0 ||
                internetReceived == 0||
                receivedPopulation == 0 ||
                receivedGoods == 0) {

            level = 0;
            return;
        }

          int targetLevel = 1;

        if (hasSecurity) {
            targetLevel = 2;
        }

        if (hasSecurity && receivedPopulation > 1 && receivedGoods > 1) {
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
                currentOutput = 2 * m +
                        Math.min(receivedPopulation, receivedGoods);
                break;
        }
    }
  public void receivePopulation(int amount) {
    this.receivedPopulation = amount;
  }
  public void resetTickValues() {
         super.resetTickValues();
        receivedPopulation = 0;
        receivedGoods = 0;
    }
}
