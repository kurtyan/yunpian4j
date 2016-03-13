package com.github.kurtyan.yunpian4j.exception;

/**
 * Created by kurtyan on 16-3-1.
 */
public class IllegalApiArgumentException extends YunpianException {

    public IllegalApiArgumentException() {
    }

    public IllegalApiArgumentException(String message) {
        super(message);
    }

    public IllegalApiArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalApiArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalApiArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
