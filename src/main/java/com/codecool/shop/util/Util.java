package com.codecool.shop.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

public class Util {

    private static Random random = new Random();

    public static String multiplyString(String s, int by) {
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(1, by).forEach(i -> sb.append(s));
        return sb.toString();
    }

    public static String toCamelCaseFromLowerCaseWithUnderScores(String s) {
        if (s.length() < 3) return s;
        try {
            if (s.charAt(0) == '_')
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(s.charAt(0)));
        int i = 1;
        while (i < s.length()) {
            if (s.charAt(i) == '_') {
                i++;
                if (i < s.length()) {
                    sb.append(Character.toUpperCase(s.charAt(i)));
                    i++;
                }
            } else {
                sb.append(Character.toLowerCase(s.charAt(i)));
                i++;
            }
        }
        return sb.toString();
    }

    public static int randRange(int start, int end) {
        return start + random.nextInt(end);
    }

    public static Timestamp getCurrentDate() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

}
