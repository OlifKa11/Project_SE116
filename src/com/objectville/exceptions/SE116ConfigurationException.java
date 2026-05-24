package com.objectville.exceptions;
import com.objectville.cells.*;
import com.objectville.engine.SimulationEngine;

/**
 * Custom exception for errors during map parsing or simulation setup.
 * Extends RuntimeException to keep the code clean and avoid mandatory try-catch blocks.
 */
public class SE116ConfigurationException extends RuntimeException {

    /**
     * Creates a new exception with a specific error message.
     * @param message Detailed description of the error.
     */
    public SE116ConfigurationException(String message) {
        super("Simulation Configuration Error: " + message);
    }
}
