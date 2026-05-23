# Objectville Simulation Project

## Architectural Design (UML)
Below is the class hierarchy and system architecture designed to ensure a decoupled and scalable simulation engine.

```mermaid
classDiagram
    %% Core Game Engine & Management
    class ObjectVilleGame {
        -CityMap map
        -ResourceManager resources
        -int totalTicks
        +main(String[] args)
        +runSimulation()
        -step1_distributeServices()
        -step2_distributeUtilities()
        -step3_distributeResources()
        -step4_updateZones()
        -step5_accumulateProduction()
    }

    class CityMap {
        -Cell[][] grid
        -int width
        -int height
        +loadMap(String filename) void
        +getCell(int x, int y) Cell
        +getNeighbors(Cell cell) List~Cell~
        +getCellsWithinRadius(Cell center, int radius) List~Cell~
    }

    class ResourceManager {
        -int pooledPopulation
        -int pooledGoods
        -int pooledLifestyle
        +distributeResources(List~Zone~ zones) void
        +accumulateProduction(List~Zone~ zones) void
    }

    %% Interfaces to satisfy OOP requirements
    class IUpdatable {
        <<interface>>
        +updateLevel() void
        +calculateOutput() void
    }

    class IServiceable {
        <<interface>>
        +receiveService(ServiceType type) void
    }

    class IUtilityReceiver {
        <<interface>>
        +receiveUtility(UtilityType type, int amount) void
    }

    class IPassable {
        <<interface>>
        +canPassUtility() boolean
    }

    %% Base Cell Hierarchy
    class Cell {
        <<abstract>>
        #int x
        #int y
        #char symbol
        +getSymbol() char
        +getX() int
        +getY() int
    }

    %% Zones
    class Zone {
        <<abstract>>
        #int level
        #int currentOutput
        #boolean hasSecurity
        #boolean hasHealth
        #boolean hasEducation
        #int electricityReceived
        #int waterReceived
        #int internetReceived
        +updateLevel()* void
        +calculateOutput()* void
        +resetTickValues() void
    }

    class Housing {
        -int consumedLifestyle
        +updateLevel() void
        +calculateOutput() void
    }

    class Industrial {
        -int consumedPopulation
        +updateLevel() void
        +calculateOutput() void
    }

    class Commercial {
        -int consumedPopulation
        -int consumedGoods
        +updateLevel() void
        +calculateOutput() void
    }

    %% Facilities (Services & Utilities)
    class Facility {
        <<abstract>>
    }

    class ServiceProvider {
        <<abstract>>
        #int radius
        #ServiceType serviceType
        +distributeService(CityMap map) void
    }

    class PoliceStation {
        +PoliceStation()
    }

    class Hospital {
        +Hospital()
    }

    class School {
        +School()
    }

    class UtilityProvider {
        <<abstract>>
        #int capacity
        #UtilityType utilityType
        +distributeUtilityBFS(CityMap map) void
    }

    class PowerPlant {
        +PowerPlant()
    }

    class WaterPumpingStation {
        +WaterPumpingStation()
    }

    class InternetHub {
        +InternetHub()
    }

    %% Infrastructure & Empty
    class Road {
        +canPassUtility() boolean
    }

    class EmptyCell {
        +canPassUtility() boolean
    }

    %% Enums for Type Safety
    class ServiceType {
        <<enumeration>>
        SECURITY
        HEALTH
        EDUCATION
    }

    class UtilityType {
        <<enumeration>>
        ELECTRICITY
        WATER
        INTERNET
    }

    %% Relationships
    ObjectVilleGame o-- CityMap : uses
    ObjectVilleGame o-- ResourceManager : uses
    CityMap *-- Cell : contains

    Cell <|-- Zone
    Cell <|-- Facility
    Cell <|-- Road
    Cell <|-- EmptyCell

    Facility <|-- ServiceProvider
    Facility <|-- UtilityProvider

    Zone ..|> IUpdatable
    Zone ..|> IServiceable
    Zone ..|> IUtilityReceiver
    
    Road ..|> IPassable
    EmptyCell ..|> IPassable

    Zone <|-- Housing
    Zone <|-- Industrial
    Zone <|-- Commercial

    ServiceProvider <|-- PoliceStation
    ServiceProvider <|-- Hospital
    ServiceProvider <|-- School

    UtilityProvider <|-- PowerPlant
    UtilityProvider <|-- WaterPumpingStation
    UtilityProvider <|-- InternetHub

    ServiceProvider --> ServiceType
    UtilityProvider --> UtilityType
