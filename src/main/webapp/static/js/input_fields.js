export class InputFieldProvider {
    static getNameInputField(fieldId) {
        return new NameInputField(fieldId);
    }

    static getEmailInputField(fieldId) {
        return new EmailInputField(fieldId);
    }
}

class InputField {
    constructor(inputFieldId) {
        this.field = document.getElementById(inputFieldId);
    }

    validate() {
        return this.field.checkValidity();
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

    validate() {
        this.testLog();
    }
}

class EmailInputField extends InputField {

    validate() {
        this.testLog()
    }
}