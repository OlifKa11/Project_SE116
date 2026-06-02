package com.objectville.cells;

public class Housing extends Zone{
   private int receivedLifestyle;
   private int oldLevel;

    public Housing(int x, int y) {
        super(x, y, 'H');
        this.receivedLifestyle = 0;
    }
    public void receiveLifestyle(int amount) {
        this.receivedLifestyle = amount;
    }

    @Override
    public void updateLevel() {
       oldLevel = level;
        
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

       if (currentOutput > 0) {
          System.out.println("House at (" + getY() + "," + getX() + ") generated " + currentOutput + " population");
       }
       printLevelChange();
    }

    private void printLevelChange() {
       if (level > oldLevel) {
          System.out.println("House at (" + getY() + "," + getX() + ") levels up from " + oldLevel + " to " + level);
       } else if (level < oldLevel) {
          System.out.println("House at (" + getY() + "," + getX() + ") levels down from " + oldLevel + " to " + level);
       }
    }
   
    public void resetTickValues() {
          super.resetTickValues();
        receivedLifestyle = 0;
    }
}
