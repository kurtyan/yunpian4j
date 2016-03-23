package com.github.kurtyan.yunpian4j.api.send;

import com.github.kurtyan.yunpian4j.client.YunpianConfig;
import com.github.kurtyan.yunpian4j.exception.YunpianException;

/**
 * Created by yanke on 2016/3/23.
 */
public class TemplatedSendMethod implements Sendable {

    private YunpianConfig yunpianConfig;
    private Sendable invoker;
    private String templateId;

    public TemplatedSendMethod(YunpianConfig yunpianConfig, Sendable invoker, String templateId) {
        this.yunpianConfig = yunpianConfig;
        this.invoker = invoker;
        this.templateId = templateId;
    }

    @Override
    public Sendable withText(String text) {
        throw new UnsupportedOperationException();
    }

    public TemplatedSendMethod appendParam(String param) {
        return this;
    }

    @Override
    public TemplatedSendMethod appendTarget(String... target) {
        invoker.appendTarget(target);
        return this;
    }

    @Override
    public SendMethodResponse execute() throws YunpianException {
        return invoker.withText("").execute();
    }

}
