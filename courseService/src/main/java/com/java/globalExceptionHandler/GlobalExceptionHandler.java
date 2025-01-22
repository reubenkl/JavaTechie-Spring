package com.java.globalExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Set<String> unrecognizedFields = new HashSet<>();

    // Handle unrecognized property exception (mismatched fields)
    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<String> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        // Add the unrecognized field to the set
        unrecognizedFields.add(ex.getPropertyName());

        // Log the unrecognized field
        logger.error("Unrecognized field: {}", ex.getPropertyName());

        // Create an error message with all unrecognized fields accumulated
        String errorMessage = "Unrecognized fields: " + String.join(", ", unrecognizedFields);

        // Log all accumulated unrecognized fields
        logger.error("All unrecognized fields: {}", String.join(", ", unrecognizedFields));

        // Clear the set of unrecognized fields for the next request
        unrecognizedFields.clear();

        // Return the response with the list of all unrecognized fields
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}