package com.github.kurtyan.yunpian4j.util;

import com.github.kurtyan.yunpian4j.exception.YunpianException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by kurtyan on 16-3-1.
 */
public class Requester {

    private static SimpleHttpClient s_httpClient = new SimpleHttpClient("utf-8");

    public static <T> T post(String url, Map<String, String> param, ResponseParser<T> responseParser) throws YunpianException {
        try {
            String response = s_httpClient.post(url, param);
            return responseParser.parse(response);
        } catch (IOException e) {
            throw new YunpianException(e);
        }
    }

    public static interface ResponseParser<T> {
        public T parse(String response) throws YunpianException;
    }

}
