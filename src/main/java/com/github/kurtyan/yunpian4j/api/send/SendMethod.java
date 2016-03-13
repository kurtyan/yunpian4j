package com.github.kurtyan.yunpian4j.api.send;

import com.github.kurtyan.yunpian4j.client.YunpianConfig;
import com.github.kurtyan.yunpian4j.exception.IllegalApiArgumentException;
import com.github.kurtyan.yunpian4j.exception.YunpianException;
import com.github.kurtyan.yunpian4j.util.Joiner;
import com.github.kurtyan.yunpian4j.util.Requester;

import java.util.*;

/**
 * Created by kurtyan on 16-3-1.
 */
public class SendMethod {

    private final static String s_sendMethodUrl = "https://sms.yunpian.com/v1/sms/send.json";
    private final static Requester.ResponseParser<SendMethodResponse> s_responseParser = new Requester.ResponseParser<SendMethodResponse>() {
        @Override
        public SendMethodResponse parse(String response) throws YunpianException {
            return null;
        }
    };

    private YunpianConfig yunpianConfig;
    private String text;
    private List<String> targetPhoneList = new ArrayList<String>();

    public SendMethod(YunpianConfig yunpianConfig) {
        this.yunpianConfig = yunpianConfig;
    }

    public SendMethod withText(String text) {
        this.text = text;
        return this;
    }

    public SendMethod appendTarget(String... target) {
        this.targetPhoneList.addAll(Arrays.asList(target));
        return this;
    }

    public SendMethod clearTarget() {
        this.targetPhoneList.clear();
        return this;
    }

    private Map<String, String> validateThenGetParam() throws IllegalApiArgumentException {
        if (text == null || text.length() == 0) {
            throw new IllegalApiArgumentException("text must not be null");
        }

        if (targetPhoneList.size() == 0) {
            throw new IllegalApiArgumentException("targetPhone must not be empty");
        }

        Map<String, String> param = new HashMap<String, String>();
        param.put("apikey", yunpianConfig.getApiKey());
        param.put("mobile", Joiner.join(this.targetPhoneList, ","));
        param.put("text", text);

        return param;
    }

    public SendMethodResponse execute() throws YunpianException {
        Map<String, String> param = this.validateThenGetParam();
        return Requester.post(
                s_sendMethodUrl,
                param,
                s_responseParser
        );
    }

}
