package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.email.client.ClientDetail;
import com.codecool.shop.controller.email.client.EmailClient;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmationRequestProcessor extends AbstractRequestProcessor {

    private SessionHandler sessionHandler = new SessionHandler();

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Order order = sessionHandler.getOrderFromSession(req);
        context.setVariable("order", order);
        context.setVariable("page_path", "confirmation/confirmation.html");
        engine.process("layout.html", context, resp.getWriter());

    }


    void sendEmailToClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ClientDetail clientDetail = new ClientDetail();
        Order order = sessionHandler.getOrderFromSession(req);
        String emailTitle = "Confirmation about the successful order at Shamans' shop";
        try {
            EmailClient.sendAsHtml(clientDetail.getRecipient(order), emailTitle,clientDetail.prepareEmailContent(order));
        } catch (MessagingException mex) {mex.printStackTrace();}
    }


}
