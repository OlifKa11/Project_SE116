package com.objectville.exceptions;

/**
 * Custom exception class used to handle specific errors encountered during
 * map parsing, coordinate validation, or general simulation configuration.
 */
public class SE116ConfigurationException extends Exception {

    /**
     * Constructs a new exception with a specific error message formatted
     * for map configuration issues.
     * @param message Detailed description of the configuration error.
     */
    public SE116ConfigurationException(String message) {
        super("Simulation Configuration Error: " + message);
    }
}