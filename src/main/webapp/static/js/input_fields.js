export class InputFieldProvider {
    static getNameInputField(fieldId) {
        return new NameInputField(fieldId);
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