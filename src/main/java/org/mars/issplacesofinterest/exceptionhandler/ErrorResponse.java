package org.mars.issplacesofinterest.exceptionhandler;


/**
 * Object represents an error response and contains detailed information about the error
 */


public class ErrorResponse {
    private String timestamp;
    private String message;
    private String details;


    public ErrorResponse(String timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



}
