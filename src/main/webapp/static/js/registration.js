import {ApiConnector} from "./api_connector.js";
import {Util} from "./util.js";

export const registration = {
    init: function () {
        closeButtons.init();
        submitter.init();
    }
};

const submitter = {

    init: function() {
        this.submitButton.addEventListener("click", this.submit);
    },

    get submitButton() {
        return document.querySelector("#registration-modal .modal-footer-submit");
    },

    submit: function () {
        ApiConnector._api_post("/user?action=registration", {"username": "username", "password": "password"}, json => {
            const {errorMessage, user} = json;
            if (errorMessage !== null) {
                console.log(errorMessage);
            } else {
                console.log(user);
            }
        })
    }
};

const closeButtons = {

    // 'this' is bound to closeButtons
    init: function() {
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

    // 'this' is bound to closeButtons
    clearValidityReport: function () {
        document.getElementById("registration-form").reset();
    }
};