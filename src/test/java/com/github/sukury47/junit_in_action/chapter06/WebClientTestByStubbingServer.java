package com.github.sukury47.junit_in_action.chapter06;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.BeforeClass;
import org.junit.Test;

public class WebClientTestByStubbingServer {
    @BeforeClass
    public static void setUp() throws Exception {
        WebClientTestByStubbingServer t = new WebClientTestByStubbingServer();
        Server server = new Server(8080);
        server.setHandler(t.new GettingHandler());
        server.setStopAtShutdown(true);
        server.start();
    }

    @Test
    public void must_receive_expected_response() throws MalformedURLException {
        WebClient client = new WebClient();
        String response = client.getContent(new URL("http://localhost:8080"));
        assertEquals("hello world", response);        
    }

    private class GettingHandler extends AbstractHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
            baseRequest.setHandled(true);
            response.getWriter().write("hello world");
        }
    }
}
