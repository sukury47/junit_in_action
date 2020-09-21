package com.github.sukury47.junit_in_action.chapter03;

public interface Controller {
    Response processRequest(Request request);
    void addHandler(Request request, RequestHandler requestHandler);    
}
