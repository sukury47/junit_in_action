package com.github.sukury47.junit_in_action.chapter03.impl;

import com.github.sukury47.junit_in_action.chapter03.Request;
import com.github.sukury47.junit_in_action.chapter03.Response;

public class ErrorResponse implements Response {
    private Request originalRequest;
    private Exception originalException;

    public ErrorResponse(Request request, Exception exception) {
        this.originalRequest = request;
        this.originalException = exception;        
    }

    public Exception getOriginalException() {
        return originalException;
    }

    public Request getOriginalRequest() {
        return originalRequest;
    }
}
