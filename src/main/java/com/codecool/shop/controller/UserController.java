package com.codecool.shop.controller;

import com.codecool.shop.controller.requestprocessing.IRequestProcessor;
import com.codecool.shop.controller.requestprocessing.RequestProcessingStrategy;
import com.codecool.shop.controller.requestprocessing.UserRequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    private IRequestProcessor requestProcessor = new UserRequestProcessor();

    private enum Action {
        REGISTRATION("registration", RequestProcessingStrategy.REGISTER_USER),
        LOGIN("login", RequestProcessingStrategy.LOGIN_USER);
        static final String parameterName = "action";
        private final String name;
        private final RequestProcessingStrategy strategy;

        Action(String name, RequestProcessingStrategy strategy) {
            this.name = name;
            this.strategy = strategy;
        }

        static RequestProcessingStrategy fromName(String name) {
            return Arrays.stream(values())
                    .filter(action -> name.equals(action.name))
                    .findFirst()
                    .map(action -> action.strategy)
                    .orElse(null);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter(Action.parameterName);
        RequestProcessingStrategy strategy = Action.fromName(actionName);
        requestProcessor.digestRequest(req, resp, strategy);
    }
}
