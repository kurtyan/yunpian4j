package com.github.kurtyan.yunpian4j.api.send;

import com.github.kurtyan.yunpian4j.exception.YunpianException;

/**
 * Created by yanke on 2016/3/23.
 */
public interface Sendable {

    public Sendable withText(String text);

    public Sendable appendTarget(String... target);

    public SendMethodResponse execute() throws YunpianException;

}
