package com.codecool.shop.util;

import java.io.*;
import java.util.stream.Collectors;

public class SQLDumpReader {

    public static String getQueryString() {

        try (BufferedReader br = new BufferedReader(
                new FileReader("./src/test/resources/db_test_dump.sql"))
        ) {
            return br.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
