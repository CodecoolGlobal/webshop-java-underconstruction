package com.codecool.shop.controller.requestprocessing;

import com.codecool.shop.controller.requestprocessing.ajax.RequestJsonConverter;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.sqlImplementation.UserDaoJDBC;
import com.codecool.shop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRequestProcessor extends AbstractRequestProcessor {

    private static final RequestJsonConverter jsonConverter = new RequestJsonConverter();
    private static final UserDao userDao = new UserDaoJDBC();
    private static final Gson userGson;
    private static final SessionHandler sessionHandler = new SessionHandler();

    static {
        userGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Override
    public void digestRequest(HttpServletRequest req, HttpServletResponse resp, RequestProcessingStrategy strategy) throws IOException {
        strategy.invokeMethod(req, resp, this);
    }

    @Override
    void defaultResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject responseJson = prepareJson();

        User userFromRequest = jsonConverter.parse(req, User.class, userGson);
        boolean validFormat = userFromRequest.getUsername() != null && userFromRequest.getUsername().length() > 0;
        boolean unique = false;
        if (validFormat) {
            unique = userDao.isUnique(userFromRequest.getUsername());
        }
        if (validFormat && unique) {
            secureUserPassword(userFromRequest);
            User insertedUser = insertUser(userFromRequest);
            if (insertedUser != null) {
                JsonElement userJson = userGson.toJsonTree(insertedUser);
                responseJson.add("user", userJson);
                sessionHandler.bindUserToSession(req, insertedUser);
            } else {
                setErrorMessage(responseJson, "Failed to safely save credentials, please try again later.");
            }
        } else if (!validFormat) {
            setErrorMessage(responseJson, "Username cannot be empty.");
        } else {
            setErrorMessage(responseJson, "Username already exists.");
        }
        sendJson(resp, responseJson.toString());
    }

    private JsonObject prepareJson() {
        JsonObject responseJson = new JsonObject();
        responseJson.add("user", null);
        responseJson.add("errorMessage", null);
        return responseJson;
    }

    private void secureUserPassword(User user) {
        String hashedPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPw);
        user.setSecure(true);
    }

    private User insertUser(User user) {
        User insertedUser;
        try {
            insertedUser = userDao.add(user);
        } catch (SecurityException e) {
            insertedUser = null;
        }
        return insertedUser;
    }

    void loginUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = prepareJson();

        User userFromSession = sessionHandler.getUserFromSession(req);
        if (userFromSession != null) {
            setErrorMessage(jsonObject, String.format("User '%s' is already logged in.", userFromSession.getUsername()));
            sendJson(resp, jsonObject.toString());
            return;
        }

        User userFromRequest = jsonConverter.parse(req, User.class, userGson);
        User userFromDB = userDao.getBy(userFromRequest.getUsername());

        if (userFromDB != null && validatePassword(userFromRequest, userFromDB)) {
            jsonObject.add("user", userGson.toJsonTree(userFromDB));
            sessionHandler.bindUserToSession(req, userFromDB);
        } else {
            setErrorMessage(jsonObject, "Invalid username or password.");
        }

        sendJson(resp, jsonObject.toString());
    }

    private boolean validatePassword(User userFromRequest, User userFromDb) {
        return BCrypt.checkpw(userFromRequest.getPassword(), userFromDb.getPassword());
    }

    private void setErrorMessage(JsonObject jsonObject, String err) {
        jsonObject.addProperty("errorMessage", err);
    }
}
