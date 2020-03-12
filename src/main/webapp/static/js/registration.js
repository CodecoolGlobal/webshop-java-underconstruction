import {ApiConnector} from "./api_connector.js";

export const registration = {
    init: function () {
        closeButtons.init();
        const submitter = new Submitter();
        submitter.init();
    }
};

function Submitter() {

    this.url = "/user?action=registration";

    this.init = function () {
        this.getSubmitButton().addEventListener("click", this.submit);
    };

    this.getSubmitButton = function () {
        return document.querySelector("#registration-modal .modal-footer-submit");
    };

    this.getAllInputs = function () {
        return Array.from(document.querySelectorAll("#registration-form input"));
    };

    this.getUsername = function () {
        return document.getElementById("registration-username");
    };

    this.getPassword = function () {
        return document.getElementById("registration-password");
    };

    this.getPasswordConfirmation = function () {
        return document.getElementById("registration-password-confirmation");
    };

    this.submit = function () {
        if (!this.validateByHtmlAttributes() || !this.validatePasswordConfirmation()) {
            return;
        }

        ApiConnector._api_post(this.url, this.collectData(), json => {
            const {errorMessage, user} = json;
            this.validateUsernameUniqueness(errorMessage);
        })
    }.bind(this);

    this.validateByHtmlAttributes = function () {
        return this.getAllInputs().every(input => {
            const result = input.checkValidity();
            if (result === false) {
                input.reportValidity();
            }
            return result;
        });
    };

    this.validatePasswordConfirmation = function () {
        const pwConfirmation = this.getPasswordConfirmation();
        const result = this.getPassword().value === pwConfirmation.value;
        if (result === false) {
            this.publishCustomValidity(pwConfirmation, "Please confirm your password correctly.");
        }
        return result;
    };

    this.collectData = function () {
        return {
            username: this.getUsername().value,
            password: this.getPassword().value
        }
    };

    this.validateUsernameUniqueness = function (errorMessage) {
        const result = errorMessage === null;
        if (result === false) {
            this.publishCustomValidity(this.getUsername(), errorMessage);
        }
        return result;
    };

    this.publishCustomValidity = function (input, message) {
        input.setCustomValidity(message);
        input.reportValidity();
        input.setCustomValidity("");
    }
}

const closeButtons = {

    init: function () {
        for (const button of this.allButtons) {
            button.addEventListener("click", this.clearValidityReport);
        }
    },

    get allButtons() {
        return [this.headerCloseButton, this.footerCloseButton];
    },

    get headerCloseButton() {
        return document.querySelector("#registration-modal .modal-header-close");
    },

    get footerCloseButton() {
        return document.querySelector("#registration-modal .modal-footer-close");
    },

    clearValidityReport: function () {
        document.getElementById("registration-form").reset();
    }
};