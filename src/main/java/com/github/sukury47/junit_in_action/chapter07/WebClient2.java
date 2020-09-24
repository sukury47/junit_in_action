package com.github.sukury47.junit_in_action.chapter07;

import java.io.IOException;
import java.io.InputStream;

public class WebClient2 {
    public String getContent(ConnectionFactory connectionFactory) {
        String result = null;
        StringBuffer content = new StringBuffer();
        InputStream is = null;
        try {
            is = connectionFactory.getData();
            int count;
            while (-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }
            result = content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                result = null;
            }
        }

        return result;
    }
}
