package com.objectville.cells;

import com.objectville.interfaces.Updatable;

public class IndustrialZone extends Cell implements Updatable {

    private int level;
    private int goodsProduced;

    public IndustrialZone(int x, int y) {
        super(x, y);
        this.level = 0;
        this.goodsProduced = 0;
    }

    @Override
    public void update() {
        goodsProduced = level * 5;
        System.out.println("Industrial zone updated.");
    }

    public int getGoodsProduced() {
        return goodsProduced;
    }
}
