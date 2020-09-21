package com.github.sukury47.junit_in_action.chapter03.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.sukury47.junit_in_action.chapter03.Controller;
import com.github.sukury47.junit_in_action.chapter03.Request;
import com.github.sukury47.junit_in_action.chapter03.RequestHandler;
import com.github.sukury47.junit_in_action.chapter03.Response;

public class DefaultController implements Controller {
    private Map<String, RequestHandler> requestHandlersByKey = new HashMap<>();

    protected RequestHandler getHandler(Request request) {
        if (!requestHandlersByKey.containsKey(request.getName())) {
            String message = "Cannot find handler for reqeust name : [" + request.getName() + "]";
            throw new RuntimeException(message);
        }

        return requestHandlersByKey.get(request.getName());
    }

    @Override
    public Response processRequest(Request request) {
        Response response = null;
        try {
            response = getHandler(request).process(request);
        } catch (Exception e) {
            response = new ErrorResponse(request, e);
        }
        return response;
    }

    @Override
    public void addHandler(Request request, RequestHandler requestHandler) {
        if (requestHandlersByKey.containsKey(request.getName())) {
            String message = "A request handler has already been registered for request name [" + request.getName() + "]";
            throw new RuntimeException(message);
        } else {
            requestHandlersByKey.put(request.getName(), requestHandler);
        }
    }
        
}