# Objectville Simulation Project

## Architectural Design (UML)
Below is the class hierarchy and system architecture designed to ensure a decoupled and scalable simulation engine.

```mermaid
classDiagram
    class Updatable {
        <<interface>>
        +update() void
    }

    class Distributable {
        <<interface>>
        +distribute(Cell[][] map) void
    }

    class Cell {
        <<abstract>>
        #x: int
        #y: int
        +getX() int
        +getY() int
    }

    class Zone {
        <<abstract>>
        #level: int
        #isPowered: boolean
        #isWatered: boolean
        #isInternetConnected: boolean
        #populationOrJobs: int
        +receiveUtility(String type) void
        +produce() void
        +resetTickData() void
    }

    class UtilityProvider {
        <<abstract>>
        #capacity: int
        #usedCapacity: int
        +canConnect(Cell target) boolean
    }

    class ServiceBuilding {
        <<abstract>>
        #radius: int
        +provideService() void
    }

    Cell <|-- Zone
    Cell <|-- UtilityProvider
    Cell <|-- ServiceBuilding
    Cell <|-- Road

    Updatable <|.. Zone
    Distributable <|.. UtilityProvider
    Distributable <|.. ServiceBuilding

    Zone <|-- ResidentialZone
    Zone <|-- IndustrialZone
    Zone <|-- CommercialZone

    UtilityProvider <|-- PowerPlant
    UtilityProvider <|-- WaterPowerPlant
    UtilityProvider <|-- InternetHub

    ServiceBuilding <|-- FireStation
    ServiceBuilding <|-- PoliceStation
    ServiceBuilding <|-- Hospital

    class SimulationEngine {
        -map: Cell[][]
        -tickCount: int
        -updatableEntities: List~Updatable~
        -distributionSystems: List~Distributable~
        +runTick() void
        +setupSimulation(Cell[][] loadedMap) void
    }

    class SE116ConfigurationException {
        +SE116ConfigurationException(String message)
    }

    SimulationEngine *-- Cell : contains
    SimulationEngine ..> SE116ConfigurationException : throws
