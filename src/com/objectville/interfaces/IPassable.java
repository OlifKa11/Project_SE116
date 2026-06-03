package com.objectville.interfaces;

// This interface determines if a utility can pass through this cell during BFS
public interface IPassable {
    boolean canPassUtility();
}