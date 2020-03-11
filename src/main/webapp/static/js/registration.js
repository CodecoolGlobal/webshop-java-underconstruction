export const registration = {
    init: function () {
        closeButtons.init();
    }
};

const closeButtons = {
    get headerCloseButton() {
        return document.querySelector("#registration-modal .modal-header-close");
    },

    get footerCloseButton() {
        return document.querySelector("#registration-modal .modal-footer-close");
    },

    get allButtons() {
        return [this.headerCloseButton, this.footerCloseButton];
    },

    // 'this' is bound to closeButtons
    init: function() {
        console.log(this.allButtons);
        for (const button of this.allButtons) {
            console.log(button);
            button.addEventListener("click", this.clearValidityReport);
        }
    },

    // 'this' is bound to closeButtons
    clearValidityReport: function () {
        document.getElementById("registration-form").reset();
    }
};