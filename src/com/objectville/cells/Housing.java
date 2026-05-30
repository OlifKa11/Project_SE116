package com.objectville.cells;

public class Housing extends Zone{
   private int receivedLifestyle;

    public Housing(int x, int y) {
        super(x, y, 'H');
        this.receivedLifestyle = 0;
    }
    public void receiveLifestyle(int amount) {
        this.receivedLifestyle = amount;
    }

    @Override
    public void updateLevel() {

        
        if (electricityReceived == 0 ||
                waterReceived == 0 ||
                internetReceived == 0) {

            level = 0;
            return;
        }
        int targetLevel = 1;

    if (hasSecurity &&
            hasHealth &&
            hasEducation) {

        targetLevel = 2;
    }

    if (hasSecurity &&
            hasHealth &&
            hasEducation &&
            receivedLifestyle > 0) {

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
                currentOutput = 2 * m + receivedLifestyle;
                break;
        }
    }
    public void resetTickData() {
        receivedLifestyle = 0;
    }
}
