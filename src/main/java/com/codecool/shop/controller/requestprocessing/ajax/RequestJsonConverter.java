package com.codecool.shop.controller.requestprocessing.ajax;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestJsonConverter {

    public String readJson(HttpServletRequest req) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        return jsonBuilder.toString();
    }

    public <T> T parse(HttpServletRequest req, Class<T> tClass) throws IOException {
        return parse(req, tClass, new Gson());
    }

    public <T> T parse(HttpServletRequest req, Class<T> tClass, Gson gson) throws IOException {
        String json = readJson(req);
        T obj = null;
        try {
            obj = gson.fromJson(json, tClass);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public String parsingSuccessJson(boolean success) {
        return String.format("{\"result\":%b}", success);
    }
}
