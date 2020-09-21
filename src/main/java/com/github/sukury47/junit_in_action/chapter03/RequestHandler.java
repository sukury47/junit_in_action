package com.github.sukury47.junit_in_action.chapter03;

public interface RequestHandler {
    Response process(Request request) throws Exception;    
}
