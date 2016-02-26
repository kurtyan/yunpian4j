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

    public String post(String url, Map<String, String> form) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null");
        }

        String charset = "utf-8";
        String queryString = this.buildQueryString(form);

        URLConnection conn = new URL(url).openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept-Charset", charset);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

        OutputStream os = conn.getOutputStream();
        os.write(queryString.getBytes(charset));
        os.flush();
        os.close();

        return this.readFromStream(conn.getInputStream(), charset);
    }

    private String buildQueryString(Map<String, String> form) {
        if (form == null || form.size() == 0) {
            return "";
        }

        StringBuilder queryBuilder = new StringBuilder();

        int i = 0;
        for (Map.Entry<String, String> entry : form.entrySet()) {
            if (i != 0) {
                queryBuilder.append("&");
            }
            i++;
            queryBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }

        return queryBuilder.toString();
    }

    private String readFromStream(InputStream is, String charset) {
        Scanner sc = new Scanner(is, charset).useDelimiter("\\A");
        return sc.hasNext() ? sc.next() : null;
    }

}
