package com.github.sukury47.junit_in_action.chapter07;

import static org.junit.Assert.assertEquals;

import com.github.sukury47.junit_in_action.chapter07.impl.MockConnectionFactory;

import org.junit.Test;

public class WebClient2Test {
    @Test
    public void must_receive_expected_response() {
        MockConnectionFactory connectionFactory = new MockConnectionFactory();
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer("hello world");
        connectionFactory.setDate(mockInputStream);
        WebClient2 client = new WebClient2();
        String result = client.getContent(connectionFactory);
        assertEquals("hello world", result);
        mockInputStream.verify();
    }
}
