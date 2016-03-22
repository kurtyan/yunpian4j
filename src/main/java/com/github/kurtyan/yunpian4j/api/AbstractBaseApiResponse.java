package com.github.kurtyan.yunpian4j.api;

import com.github.kurtyan.yunpian4j.exception.YunpianBusinessException;

/**
 * Most yunpian api response possesses a CODE property and a MSG property
 * This class serve as a basic container containing the CODE / MSG property.
 *
 * Created by yanke on 2016/3/23.
 */
public class AbstractBaseApiResponse<T extends AbstractBaseApiResponse> {

    public int code;
    public String msg;

    public T validateAndGet() throws YunpianBusinessException {
        if (code == 0) {
            return (T) this;
        } else {
            throw new YunpianBusinessException(msg, code);
        }
    }

}
