export const registration = {
    init: function () {
        closeButtons.init();
    }
};

const submitter = {

    init: function() {

    },

    get submitButton() {
        return document.querySelector("#registration-modal .modal-footer-submit");
    },
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