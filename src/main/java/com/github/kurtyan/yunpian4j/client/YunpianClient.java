package com.github.kurtyan.yunpian4j.client;

import com.github.kurtyan.yunpian4j.api.call.CallMethod;
import com.github.kurtyan.yunpian4j.api.send.SendMethod;

/**
 * Created by kurtyan on 16-3-1.
 */
public class YunpianClient {

    private YunpianConfig yunpianConfig;

    public SendMethod createSendMethod() {
        return new SendMethod(yunpianConfig);
    }

    public CallMethod createCallMethod() {
        return new CallMethod(yunpianConfig.getApiKey());
    }

}
