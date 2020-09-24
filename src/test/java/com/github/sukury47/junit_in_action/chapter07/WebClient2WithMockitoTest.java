package com.github.sukury47.junit_in_action.chapter07;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import org.junit.Test;

public class WebClient2WithMockitoTest {
    @Test
    public void must_receive_expected_response() throws Exception {
        InputStream is = mock(InputStream.class);

        String message = "hello world";
        //char[] messageCharArray = message.toCharArray();
        when(is.read())
            .thenReturn(Integer.valueOf((byte)'h'))
            .thenReturn(Integer.valueOf((byte)'e'))
            .thenReturn(Integer.valueOf((byte)'l'))
            .thenReturn(Integer.valueOf((byte)'l'))
            .thenReturn(Integer.valueOf((byte)'o'))
            .thenReturn(Integer.valueOf((byte)' '))
            .thenReturn(Integer.valueOf((byte)'w'))
            .thenReturn(Integer.valueOf((byte)'o'))
            .thenReturn(Integer.valueOf((byte)'r'))
            .thenReturn(Integer.valueOf((byte)'l'))
            .thenReturn(Integer.valueOf((byte)'d'))
            .thenReturn(-1);
        
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        when(connectionFactory.getData()).thenReturn(is);

        WebClient2 client = new WebClient2();
        String result = client.getContent(connectionFactory);
        assertEquals(message, result);

        verify(is, times(12)).read();
        verify(is, times(1)).close();
    }
}
