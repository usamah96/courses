package com.service.user.responses;

public class Response<T> {

    private T response;
    private boolean isError = false;
    private String errorMessage;

    public Response(T response){
        this.response = response;
    }

    public Response(boolean isError, String errorMessage){
        this.errorMessage = errorMessage;
        this.isError = isError;
    }
}
