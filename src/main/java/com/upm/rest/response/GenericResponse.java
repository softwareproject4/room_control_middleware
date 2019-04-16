package com.upm.rest.response;

/**
 *
 * @author HP
 */
public class GenericResponse {
 
    private String message;
    
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
