package org.mars.issplacesofinterest.exceptionhandler;

public class ISSLocationException extends RuntimeException {
    public ISSLocationException(String message) {
        super(message);
    }
}