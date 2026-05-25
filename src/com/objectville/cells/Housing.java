package com.objectville.cells;

public class Housing extends Zone {

    private int consumedLifestyle;

    public Housing(int x, int y) {
        super(x, y, 'H');
        this.consumedLifestyle = 0;
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public void calculateOutput() {

    }
}
