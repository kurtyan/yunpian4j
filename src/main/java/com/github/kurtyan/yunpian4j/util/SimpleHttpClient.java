package com.github.kurtyan.yunpian4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple http client written in pure java, based on java.net.URLConnection.
 * At this time, http methods other than 'POST with urlencoded form' are not implemented because they are not necessary in this project.
 *
 * Created by kurtyan on 16-2-26.
 */
public class SimpleHttpClient {

    private String charset;

    public SimpleHttpClient() {
        this("utf-8");
    }

    public SimpleHttpClient(String charset) {
        this.charset = charset;
    }

    public String post(String url, Map<String, String> form) throws IOException {
        return this.post(url, form, new InputStreamParser<String>() {
            @Override
            public String parse(InputStream is) {
                return SimpleHttpClient.this.readFromStream(is, charset);
            }
        });
    }

    public <T> T post(String url, Map<String, String> form, InputStreamParser<T> responseParser) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null");
        }

        String queryString = this.buildQueryString(form);

        URLConnection conn = new URL(url).openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept-Charset", charset);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

        OutputStream os = conn.getOutputStream();
        os.write(queryString.getBytes(charset));
        os.flush();
        os.close();

        return this.parseResponse(conn, responseParser);
    }

    private <T> T parseResponse(URLConnection conn, InputStreamParser<T> parser) throws IOException {
        InputStream is = null;

        try {
            is = conn.getInputStream();
            return parser.parse(is);
        } finally {
            if (is != null) {
                // JDK6 sucks T_T
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private String buildQueryString(Map<String, String> form) {
        if (form == null || form.size() == 0) {
            return "";
        }

        StringBuilder queryBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : form.entrySet()) {
            queryBuilder.append("&")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }

        return queryBuilder.substring(1);
    }

    private String readFromStream(InputStream is, String charset) {
        Scanner sc = new Scanner(is, charset).useDelimiter("\\A");
        return sc.hasNext() ? sc.next() : null;
    }

    public static interface InputStreamParser<T> {
        public T parse(InputStream is);
    }

}
