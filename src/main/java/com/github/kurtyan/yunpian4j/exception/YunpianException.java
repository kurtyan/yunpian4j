package com.github.kurtyan.yunpian4j.exception;

/**
 * Created by kurtyan on 16-3-1.
 */
public class YunpianException extends Exception {

    public YunpianException() {
    }

    public YunpianException(String message) {
        super(message);
    }

    public YunpianException(String message, Throwable cause) {
        super(message, cause);
    }

    public YunpianException(Throwable cause) {
        super(cause);
    }

}
