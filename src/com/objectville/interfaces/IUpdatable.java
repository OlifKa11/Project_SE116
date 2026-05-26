package com.objectville.interfaces;

// Ensures that the object can update its state and output per tick
public interface IUpdatable {
    void updateLevel();
    void calculateOutput();
}