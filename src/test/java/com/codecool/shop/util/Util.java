package com.codecool.shop.util;

import java.util.stream.IntStream;

public class Util {

    public static String multiplyString(String s, int by) {
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(1, by).forEach(i -> sb.append(s));
        return sb.toString();
    }
}
