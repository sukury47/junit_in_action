package com.github.sukury47.junit_in_action.chapter03.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import com.github.sukury47.junit_in_action.chapter03.Request;
import com.github.sukury47.junit_in_action.chapter03.RequestHandler;
import com.github.sukury47.junit_in_action.chapter03.Response;

import org.junit.Before;
import org.junit.Test;

public class DefaultControllerTest {
    private DefaultController controller;
    private Request request;
    private RequestHandler handler;
    
    @Before
    public void instantiate() {
        controller = new DefaultController();
        request = new SampleRequest();
        handler = new SampleHandler();
        
        controller.addHandler(request, handler);
    }
    
    @Test
    public void testAddHandler() {
        RequestHandler handler2 = controller.getHandler(request);
        assertSame("Handler we set in controller should be the same handler we get", handler, handler2);        
    }

    @Test
    public void testProcessRequest() {
        Response response = controller.processRequest(request);
        assertNotNull("Must not return a null response", response);
        assertEquals("Response should be of type SampleResponse", SampleResponse.class, response.getClass());
    }

    @Test
    public void exception_throwing_request_handler_must_return_error_response() {
        SampleRequest request = new SampleRequest("testError");
        SampleExceptionHandler handler = new SampleExceptionHandler();
        controller.addHandler(request, handler);
        Response response = controller.processRequest(request);
        assertNotNull("Must not return a null response", response);
        assertEquals(ErrorResponse.class, response.getClass());
    }

    @Test(expected = RuntimeException.class)
    public void undefined_request_handler_must_throw_exception() {
        SampleRequest request = new SampleRequest("testNotDefineda");
        controller.getHandler(request);
    }

    @Test(expected = RuntimeException.class)
    public void duplicated_request_handler_must_throw_exception() {
        SampleRequest request = new SampleRequest();
        RequestHandler handler = new SampleHandler();
        controller.addHandler(request, handler);
    }

    private class SampleRequest implements Request {
        private static final String DEFAULT_NAME = "Test";
        private String name;

        public SampleRequest(String name) {
            this.name = name;            
        }

        public SampleRequest() {
            this(DEFAULT_NAME);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SampleRequest) {
                SampleRequest _obj = (SampleRequest)obj;
                return _obj.getName().equals(this.getName());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }

    }

    private class SampleResponse implements Response {

    }

    private class SampleHandler implements RequestHandler {

        @Override
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    private class SampleExceptionHandler implements RequestHandler {
        @Override
        public Response process(Request request) throws Exception {
            throw new Exception("error processing request");
        }
    }
}
