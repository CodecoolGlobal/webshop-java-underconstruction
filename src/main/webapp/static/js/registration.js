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

    registrationSubmitter.submit = function () {
        if (!this.validateByHtmlAttributes() || !this.validatePasswordConfirmation()) {
            return;
        }

        ApiConnector._api_post(this.url, this.collectData(), json => {
            const {errorMessage, user} = json;
            this.validateUsernameUniqueness(errorMessage);
        })
    }.bind(registrationSubmitter);

    registrationSubmitter.validatePasswordConfirmation = function () {
        const result = this.password.value === this.passwordConfirmation.value;
        if (result === false) {
            this.publishCustomValidity(this.passwordConfirmation, "Please confirm your password correctly.");
        }
        return result;
    }.bind(registrationSubmitter);

    registrationSubmitter.validateUsernameUniqueness = function (errorMessage) {
        const result = errorMessage === null;
        if (result === false) {
            this.publishCustomValidity(this.username, errorMessage);
        }
        return result;
    }.bind(registrationSubmitter);

    return registrationSubmitter;
}