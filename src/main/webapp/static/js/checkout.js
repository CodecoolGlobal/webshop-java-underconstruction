import {InputFieldProvider} from "./input_fields.js";

class Main {
    static init() {

        const container = new InputFieldContainer();
        container.addField(InputFieldProvider.getNameInputField("first-name"));
        container.addField(InputFieldProvider.getNameInputField("last-name"));

        const submitButton = document.getElementById("checkout-submit");
        submitButton.addEventListener("click", function () {
            container.validateFields();
        })
    }
}

class InputFieldContainer {

    constructor() {
        this.inputFields = [];
    }

    addField(inputField) {
        this.inputFields.push(inputField);
    }

    validateFields() {
        for (let fieldWrapper of this.inputFields) {
            fieldWrapper.validate();
        }
    }
}

window.onload = Main.init;