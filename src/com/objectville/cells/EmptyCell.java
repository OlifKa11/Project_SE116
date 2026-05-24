package com.objectville.cells;

import com.objectville.interfaces.Passable;

public class EmptyCell extends Cell implements Passable {
    public EmptyCell(int x, int y){
        super(x, y);
    }
    @Override
    public boolean canPassUtility(){
        return true;
    }

}
