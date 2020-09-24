package com.github.sukury47.junit_in_action.chapter07.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.sukury47.junit_in_action.chapter07.ConnectionFactory;

public class HttpURLConnectionFactory implements ConnectionFactory {
    private URL url;

    public HttpURLConnectionFactory(URL url) {
        this.url = url;
    }
    
    @Override
    public InputStream getData() throws Exception {
        return ((HttpURLConnection)url.openConnection()).getInputStream();
    }    
}
