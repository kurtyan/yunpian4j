package com.github.kurtyan.yunpian4j.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by kurtyan on 16-2-26.
 */
public class SimpleHttpClientTest {

    @Test
    public void testPost() throws IOException {
        String url = "https://sms.yunpian.com/v1/sms/send.json";
        SimpleHttpClient client = new SimpleHttpClient();
        Map<String, String> form = new HashMap<String, String>();
        form.put("apikey", "0");
        form.put("mobile", "13800138000");
        String result = client.post(url, form);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("\"code\":1"));
    }

}