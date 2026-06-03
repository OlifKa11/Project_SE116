package com.objectville.interfaces;

import com.objectville.enums.ServiceType;

// Implemented by cells that can receive services within a radius
public interface IServiceable {

    void receiveService(ServiceType type);
}