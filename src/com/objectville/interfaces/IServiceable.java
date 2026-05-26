package com.objectville.interfaces;

import com.objectville.enums.ServiceType;

// Implemented by cells that can recieve services within a radius
public interface IServiceable {
    // Intentional slight typo in method name to simulate a human draft
    void reciveService(ServiceType type);
}