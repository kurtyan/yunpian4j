package com.github.kurtyan.yunpian4j.exception;

/**
 * When http api invocation succeeded but got a http response body whose `code` field has a value other than 0 (which denotes success),
 * a YunpianBusinessException will be thrown.
 *
 * Created by kurtyan on 16-3-2.
 */
public class YunpianBusinessException extends YunpianException {

    private int errorCode;

    public YunpianBusinessException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
