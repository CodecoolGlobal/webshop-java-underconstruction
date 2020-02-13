import {InputFieldReader} from "./input_fields.js";
import {ApiConnector} from "./api_connector.js";

class Main {
    static init() {

        const container = new InputFieldContainer();
        const inputFieldIdCollection = [
            "first-name", "last-name", "email", "phone-number",
            "billing-country", "billing-city", "billing-zip-code", "billing-address",
            "shipping-country", "shipping-city", "shipping-zip-code", "shipping-address"
        ];

        inputFieldIdCollection.forEach(fieldId => container.addField(InputFieldReader.getInputField(fieldId)));
        document.getElementById("checkout-submit")
            .addEventListener("click", () => {
                container.validateFields();
                if (container.inputsValid) {
                    ApiConnector._api_post("/checkout",OrderCredentialsCollector.collectData(), resp => {
                        console.log("response arrived");
                    })
                }
            });

    }
}

class OrderCredentialsCollector {

    static collectData() {
        return {
            firstName: InputFieldReader.getFieldValue("first-name"),
            lastName: InputFieldReader.getFieldValue("last-name"),
            email: InputFieldReader.getFieldValue("email"),
            phoneNumber: InputFieldReader.getFieldValue("phone-number"),
            billingDetails: {
                country: InputFieldReader.getBillingDetail("country"),
                city: InputFieldReader.getBillingDetail("city"),
                zipCode: InputFieldReader.getBillingDetail("zip-code"),
                address: InputFieldReader.getBillingDetail("address")
            },
            shippingDetails: {
                country: InputFieldReader.getShippingDetail("country"),
                city: InputFieldReader.getShippingDetail("city"),
                zipCode: InputFieldReader.getShippingDetail("zip-code"),
                address: InputFieldReader.getShippingDetail("address")
            }
        }
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