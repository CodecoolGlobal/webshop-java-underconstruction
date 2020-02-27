package com.codecool.shop.util;

import java.util.Random;
import java.util.stream.IntStream;

public class Util {

    private static Random random = new Random();

    public static String multiplyString(String s, int by) {
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(1, by).forEach(i -> sb.append(s));
        return sb.toString();
    }

    public static int randRange(int start, int end) {
        return start + random.nextInt(end);
    }
}
