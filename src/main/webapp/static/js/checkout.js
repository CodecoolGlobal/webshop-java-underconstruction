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
                    const cred = OrderCredentialsCollector.collectData();
                    console.log(JSON.stringify(cred, null, 2));
                    ApiConnector._api_post("/checkout", cred, resp => {
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
        this.inputsValid = this.inputFields.every(inputField => inputField.validate());
    }

    get inputsValid() {
        return this.validated;
    }

    set inputsValid(validity) {
        this.validated = validity;
    }
}

window.onload = Main.init;