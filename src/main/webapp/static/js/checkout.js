import {InputFieldProvider} from "./input_fields.js";

class Main {
    static init() {

        const container = new InputFieldContainer();
        container.addField(InputFieldProvider.getInputField("first-name"));
        container.addField(InputFieldProvider.getInputField("last-name"));
        container.addField(InputFieldProvider.getInputField("email"));
        container.addField(InputFieldProvider.getInputField("phone-number"));
        container.addField(InputFieldProvider.getInputField("billing-country"));
        container.addField(InputFieldProvider.getInputField("shipping-country"));
        container.addField(InputFieldProvider.getInputField("billing-city"));
        container.addField(InputFieldProvider.getInputField("shipping-city"));
        container.addField(InputFieldProvider.getInputField("billing-zip-code"));
        container.addField(InputFieldProvider.getInputField("shipping-zip-code"));
        container.addField(InputFieldProvider.getInputField("billing-address"));
        container.addField(InputFieldProvider.getInputField("shipping-address"));

        document.getElementById("checkout-submit")
            .addEventListener("click", () => {
                container.validateFields();
                console.log("Validity of inputs: " + container.inputsValid);
            });

    }
}

class InputFieldContainer {

    constructor() {
        this.inputFields = [];
        this.validated = false;
    }

    addField(inputField) {
        this.inputFields.push(inputField);
    }

    validateFields() {
        let validity = false;
        for (let inputField of this.inputFields) {
            if (inputField.validate() === false) {
                validity = false;
                // return;
            }
        }
        // this.inputsValid = true;
        return validity;
    }

    get inputsValid() {
        return this.validated;
    }

    set inputsValid(validity) {
        this.validated = validity;
    }
}

window.onload = Main.init;