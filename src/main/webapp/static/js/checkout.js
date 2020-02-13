import {EventProvider} from "./events.js";

class Main {
    static init() {

        const container = new InputFieldContainer();
        container.addField(new NameInputField("first-name"));
        container.addField(new NameInputField("last-name"));
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

class InputField {
    constructor(inputFieldId) {
        this.field = document.getElementById(inputFieldId);
    }

    validate() {
        return this.field.checkValidity();
    }

}

class NameInputField extends InputField {
    constructor(inputFieldId) {
        super(inputFieldId);
    }

    validate() {
        if (super.validate()) {
            console.log("Valid input");
        } else {
            console.log("Invalid input");
        }
    }
}

window.onload = Main.init;