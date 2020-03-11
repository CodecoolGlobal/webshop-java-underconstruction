export class InputFieldReader {

    static getInputField(fieldId) {
        return new InputField(fieldId);
    }

    static getFieldValue(id) {
        return document.getElementById(id).value;
    }

    static getBillingDetail(detail) {
        return document.getElementById(`billing-${detail}`).value;
    }

    static getShippingDetail(detail) {
        return document.getElementById(`shipping-${detail}`).value;
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