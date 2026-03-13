package edu.naukma.exeptions;

public class InvalidMenuItemFieldException extends RuntimeException {
    public InvalidMenuItemFieldException(String message) {
        super(message);
    }
}
