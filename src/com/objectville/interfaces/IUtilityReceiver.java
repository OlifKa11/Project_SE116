package com.objectville.interfaces;

import com.objectville.enums.UtilityType;

// Implemented by cells that consume utilities like water or electricity
public interface IUtilityReceiver {
    void receiveUtility(UtilityType type, int amount);
}