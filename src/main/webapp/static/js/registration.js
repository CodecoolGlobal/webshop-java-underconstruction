import {ApiConnector} from "./api_connector.js";
import {ModalCloser} from "./modals.js";
import {submitterBase} from "./submitter.js";

export const registration = {
    init: function () {
        new ModalCloser("registration").init();
        createRegistrationSubmitter().init();
    }
};

function createRegistrationSubmitter() {
    const registrationSubmitter = Object.create(submitterBase);
    registrationSubmitter.setAction("registration");
    registrationSubmitter.passwordConfirmation = document.getElementById("registration-password-confirmation");
    registrationSubmitter.submit = submitRegistration.bind(registrationSubmitter);
    return registrationSubmitter;
}

function submitRegistration() {
    if (!this.validateByHtmlAttributes() || !validatePasswordConfirmation.call(this)) {
        return;
    }

    ApiConnector._api_post(this.url, this.collectData(), json => {
        const {errorMessage, user} = json;
        validateUsernameUniqueness.call(this, errorMessage);
    })
}

function validatePasswordConfirmation() {
    const result = this.password.value === this.passwordConfirmation.value;
    if (result === false) {
        this.publishCustomValidity(this.passwordConfirmation, "Please confirm your password correctly.");
    }
    return result;
}

function validateUsernameUniqueness(errorMessage) {
    const result = errorMessage === null;
    if (result === false) {
        this.publishCustomValidity(this.username, errorMessage);
    }
    return result;
}