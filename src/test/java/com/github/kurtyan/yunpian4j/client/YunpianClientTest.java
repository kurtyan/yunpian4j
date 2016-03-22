package com.github.kurtyan.yunpian4j.client;

import com.github.kurtyan.yunpian4j.api.send.SendMethodResponse;
import com.github.kurtyan.yunpian4j.exception.YunpianBusinessException;
import com.github.kurtyan.yunpian4j.exception.YunpianException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanke on 2016/3/23.
 */
public class YunpianClientTest {

    private YunpianClient client;

    @Before
    public void setUp() {
        YunpianConfig config = new YunpianConfig();
        config.setApiKey("1");
        client = new YunpianClient(config);
    }

    @Test(expected = YunpianBusinessException.class)    // invalid api key
    public void testSend() throws YunpianException {
        SendMethodResponse response = client.createSendMethod().withText("hahaha").appendTarget("13800138000").execute();
    }

}