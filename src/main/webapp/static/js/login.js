import {ApiConnector} from "./api_connector.js";
import {modalCloser} from "./modals.js";
import {submitterBase} from "./submitter.js";

export const login = {
    init: function () {
        const closer = Object.create(modalCloser);
        closer.setAction("login");

        const loginSubmitter = Object.create(submitterBase);
        loginSubmitter.setAction("login");
        loginSubmitter.submit = submitLogin.bind(loginSubmitter);

        closer.init();
        loginSubmitter.init();
    }
};

function submitLogin() {
    if (!this.validateByHtmlAttributes()) {
        return;
    }

    ApiConnector._api_post(this.url, this.collectData(), json => {
        const {errorMessage, user} = json;
        console.log(user);
        validateLogin.call(this, errorMessage);
    });
}

function validateLogin(errorMessage) {
    const validLogin = errorMessage === null;
    if (validLogin === false) {
        this.publishCustomValidity(this.username, errorMessage);
    }
    return validLogin;
}