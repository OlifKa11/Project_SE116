package com.objectville.cells;

public class Commercial extends Zone {
  private int consumedPopulation;
    private int consumedGoods;

    public Commercial(int x, int y) {
        super(x, y, 'C');
        this.consumedPopulation = 0;
        this.consumedGoods = 0;
    }

    public void consumeGoods(int amount) {
        this.consumedGoods = amount;
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
                consumedPopulation > 0 &&
                consumedGoods > 0) {

            if (level < 3) {
                level++;
            }

            return;
        }

        
        if (hasSecurity &&
                hasHealth &&
                hasEducation) {

            if (level < 2) {
                level++;
            }

            return;
        }

       
        if (level < 1) {
            level++;
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
                currentOutput = 2 * m +
                        Math.min(consumedPopulation, consumedGoods);
                break;
        }
    }
    public void resetTickData() {
        consumedPopulation = 0;
        consumedGoods = 0;
    }
}
