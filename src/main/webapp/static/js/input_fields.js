export class InputFieldReader {

    static getInputField(fieldId) {
        switch (fieldId) {
            case "first-name":
            case "last-name":
                return new NameInputField(fieldId);
            case "email":
                return new EmailInputField(fieldId);
            case "phone-number":
                return new PhoneNumberInputField(fieldId);
            case "billing-country":
            case "shipping-country":
                return new CountryInputField(fieldId);
            case "billing-city":
            case "shipping-city":
                return new CityInputField(fieldId);
            case "billing-zip-code":
            case "shipping-zip-code":
                return new ZipCodeInputField(fieldId);
            case "billing-address":
            case "shipping-address":
                return new AddressInputField(fieldId);
        }
    }

    static getFieldValue(id) {
        return document.getElementById(id).value;
    }

    static getBillingDetail(detail) {
        return document.getElementById(`billing-${detail}`);
    }

    static getShippingDetail(detail) {
        return document.getElementById(`shipping-${detail}`);
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

}

class EmailInputField extends InputField {

}

class PhoneNumberInputField extends InputField {

}

class CountryInputField extends InputField {

}

class CityInputField extends InputField {

}

class ZipCodeInputField extends InputField {

}

class AddressInputField extends InputField {

}