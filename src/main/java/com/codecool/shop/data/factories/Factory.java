package com.codecool.shop.data.factories;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Factory {

    public void buildDao(BufferedReader buffer) throws IOException {
        String line;
        while ((line = buffer.readLine()) != null) {
            String[] entries = line.split(",");
            synthesize(entries);
        }
    }

    protected abstract void synthesize(String[] entries);
}
