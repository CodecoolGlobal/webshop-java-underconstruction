package com.codecool.shop.controller.requestprocessing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IRequestProcessor {
    void digestRequest(HttpServletRequest request, HttpServletResponse response, RequestProcessingStrategy strategy) throws IOException;
}
