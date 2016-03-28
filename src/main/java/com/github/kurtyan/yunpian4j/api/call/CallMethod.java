package com.github.kurtyan.yunpian4j.api.call;

import com.github.kurtyan.yunpian4j.api.ResponseParserFactory;
import com.github.kurtyan.yunpian4j.client.YunpianConfig;
import com.github.kurtyan.yunpian4j.exception.IllegalApiArgumentException;
import com.github.kurtyan.yunpian4j.exception.YunpianException;
import com.github.kurtyan.yunpian4j.util.Requester;
import com.github.kurtyan.yunpian4j.util.Requester.ResponseParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kurtyan on 16-3-1.
 */
public class CallMethod {

    private static String s_callMethodUrl = "https://voice.yunpian.com/v1/voice/send.json";
    private static ResponseParser<CallMethodResponse> s_responseParser = ResponseParserFactory.getInstance().getParser(CallMethodResponse.class);

    private YunpianConfig yunpianConfig;
    private String code;
    private String target;

    public CallMethod(YunpianConfig yunpianConfig) {
        this.yunpianConfig = yunpianConfig;
    }

    public CallMethod withCode(String code) {
        this.code = code;
        return this;
    }

    public CallMethod withTarget(String target) {
        this.target = target;
        return this;
    }

    private Map<String, String> validateAndGetParam() throws IllegalApiArgumentException {
        // TODO: add isDigit check and length (range 4-6) check
        if (code == null || code.length() == 0) {
            throw new IllegalApiArgumentException("code must not be null");
        }

        if (target == null || target.length() == 0) {
            throw new IllegalApiArgumentException("target must not be null");
        }

        Map<String, String> param = new HashMap<String, String>();
        param.put("apikey", yunpianConfig.getApiKey());
        param.put("code", code);
        param.put("mobile", target);

        return param;
    }

    public CallMethodResponse execute() throws YunpianException {
        return Requester.post(
                s_callMethodUrl,
                this.validateAndGetParam(),
                s_responseParser
        );
    }

}
