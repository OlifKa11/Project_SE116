package com.objectville.interfaces;

import com.objectville.enums.ServiceType;

// Implemented by cells that can recieve services within a radius
public interface IServiceable {

    void reciveService(ServiceType type);
}