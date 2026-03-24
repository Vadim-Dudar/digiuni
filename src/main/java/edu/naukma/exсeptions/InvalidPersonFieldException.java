package edu.naukma.exсeptions;

public class InvalidPersonFieldException extends IllegalArgumentException {
    public InvalidPersonFieldException(String message) {
        super(message);
    }
    public InvalidPersonFieldException(String message, Throwable cause) { super(message, cause); }
}
