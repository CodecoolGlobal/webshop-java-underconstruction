package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IRequestProcessor {
    void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException;
    boolean queryStringPresent(HttpServletRequest req);
    boolean filteredFaviconRequest(HttpServletRequest req, HttpServletResponse resp);
}
