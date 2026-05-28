package com.objectville.cells;

public class Housing extends Zone{
   private int recievedLifestyle;

    public Housing(int x, int y) {
        super(x, y, 'H');
        this.recievedLifestyle = 0;
    }
    public void recieveLifestyle(int amount) {
        this.recievedLifestyle = amount;
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
                recievedLifestyle > 0) {

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
                currentOutput = 2 * m + recievedLifestyle;
                break;
        }
    }
    public void resetTickData() {
        recievedLifestyle = 0;
    }
}
