package edu.naukma.exсeptions;

public class InvalidMenuItemFieldException extends RuntimeException {
    public InvalidMenuItemFieldException(String message) {
        super(message);
    }
}
