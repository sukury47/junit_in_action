package com.github.sukury47.junit_in_action.chapter06;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class WebClientTestByStubbingConnectionTest {

    @BeforeClass
    public static void setUp() {
        WebClientTestByStubbingConnectionTest t = new WebClientTestByStubbingConnectionTest();
        URL.setURLStreamHandlerFactory(t.new StubHttpStreamHandlerFactory());
    }

    @Test
    public void must_accept_expected_response() throws MalformedURLException {
        WebClient client = new WebClient();
        String response = client.getContent(new URL("http://localhost:8080"));
        assertEquals("hello world", response);
    }

    private class StubHttpStreamHandlerFactory implements URLStreamHandlerFactory {
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpUrlStreamHandler();
        }
    }

    private class StubHttpUrlStreamHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL url) throws IOException {
            return new StubHttpUrlConnection(url);
        }
    }

    private class StubHttpUrlConnection extends HttpURLConnection {
        private boolean isInput = true;
        protected StubHttpUrlConnection(URL url) {
            super(url);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (!isInput) {
                throw new ProtocolException("Cannot read from URLConnection if doInput=false (call setDoInput(true)");
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(new String("hello world").getBytes());
            return bais;
        }
        
        @Override
        public void disconnect() {

        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {

        }
    }
}
