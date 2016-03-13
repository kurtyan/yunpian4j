package com.github.kurtyan.yunpian4j.util;

import java.util.List;

/**
 * Created by kurtyan on 16-3-1.
 */
public class Joiner {

    public static String join(List<String> list, String separator) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list must not be empty");
        }
        if (separator == null) {
            throw new IllegalArgumentException("separator must not be null");
        }

        int listSize = list.size();
        if (listSize == 1) {
            return list.get(0);
        }

        StringBuilder sb = new StringBuilder(list.get(0));
        for (int i = 1; i < listSize; i++) {
            sb.append(separator).append(list.get(i));
        }
        return sb.toString();
    }

}
