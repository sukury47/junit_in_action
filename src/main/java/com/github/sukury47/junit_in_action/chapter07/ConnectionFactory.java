package com.github.sukury47.junit_in_action.chapter07;

import java.io.InputStream;

public interface ConnectionFactory {
    InputStream getData() throws Exception;    
}
