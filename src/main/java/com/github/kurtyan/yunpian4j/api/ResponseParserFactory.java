package com.github.kurtyan.yunpian4j.api;

import com.github.kurtyan.yunpian4j.exception.ResponseParsingException;
import com.github.kurtyan.yunpian4j.exception.YunpianException;
import com.github.kurtyan.yunpian4j.util.Requester;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanke on 2016/3/23.
 */
public class ResponseParserFactory {

    private Gson gson = new Gson();

    public <T extends AbstractBaseApiResponse> Requester.ResponseParser<T> getParser(final Class<T> responseType) {
        return new Requester.ResponseParser<T>() {
            @Override
            public T parse(String response) throws YunpianException {
                try {
                    return (T) gson.getAdapter(responseType).fromJson(response).validateAndGet();
                } catch (IOException e) {
                    throw new ResponseParsingException();
                }
            }
        };
    }



    private static class Lazy {
        private static ResponseParserFactory inst = new ResponseParserFactory();
    }

    public static ResponseParserFactory getInstance() {
        return Lazy.inst;
    }

}
