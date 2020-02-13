export class InputFieldProvider {
    static getNameInputField(fieldId) {
        return new NameInputField(fieldId);
    }

    static getEmailInputField(fieldId) {
        return new EmailInputField(fieldId);
    }

    static getPhoneNumberInputField(fieldId) {
        return new PhoneNumberInputField(fieldId);
    }
}

class InputField {
    constructor(inputFieldId) {
        this.field = document.getElementById(inputFieldId);
    }

    validate() {
        this.testLog();
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