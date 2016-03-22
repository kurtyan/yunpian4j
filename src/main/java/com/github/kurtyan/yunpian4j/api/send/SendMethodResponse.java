package com.github.kurtyan.yunpian4j.api.send;

import com.github.kurtyan.yunpian4j.api.AbstractBaseApiResponse;

import java.math.BigDecimal;

/**
 * Created by kurtyan on 16-3-1.
 */
public class SendMethodResponse extends AbstractBaseApiResponse<SendMethodResponse> {

    public static class Result {
        public int count;
        public BigDecimal fee;
        public long sid;
    }

    public Result result;

}
