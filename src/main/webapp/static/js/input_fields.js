export class InputFieldProvider {

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
}

class InputField {
    constructor(inputFieldId) {
        this.field = document.getElementById(inputFieldId);
    }

    validate() {
        this.testLog();
        return false;
    }

    testLog() {
        if (this.field.checkValidity()) {
            this.logValid();
        } else {
            this.logInvalid();
        }
    }

    logValid() {
        console.log("Valid input for: " + this.field.id);
    }

    logInvalid() {
        console.log("Invalid input for: " + this.field.id);
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