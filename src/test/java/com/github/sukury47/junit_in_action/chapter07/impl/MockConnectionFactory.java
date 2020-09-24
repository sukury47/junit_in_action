package com.github.sukury47.junit_in_action.chapter07.impl;

import java.io.InputStream;

import com.github.sukury47.junit_in_action.chapter07.ConnectionFactory;

public class MockConnectionFactory implements ConnectionFactory {
    private InputStream inputStream;

    public void setDate(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getData() throws Exception {
        return inputStream;
    }
    
}
