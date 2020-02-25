package com.codecool.shop.controller.requestprocessing;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentRequestProcessor implements RequestProcessor {


    @Override
    public String extractJson(HttpServletRequest req) {
        return null;
    }

    @Override
    public void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {



    }

    @Override
    public void manipulateDao(HttpServletRequest req) {

    }
}
