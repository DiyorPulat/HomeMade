package org.example.homemade2.exceptions;

public class ShouldBeUniqueException extends RuntimeException{
    public ShouldBeUniqueException(String message) {
        super(message);
    }
}
