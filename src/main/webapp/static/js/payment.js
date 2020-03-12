

// REFACTOR

class Main {
    static init() {
        const paymentMethodChooser = new PaymentMethodChooser();
        paymentMethodChooser.choosePaymentMethodListener();

        document.querySelector("#purchase-submit")
            .addEventListener("click", () => {
                paymentMethodChooser.getInputValidator().confirmationListener();
            });


    }
}

class InputValidator {

    constructor(chosenPaymentMethod) {
        this.inputFields = document.querySelectorAll(".form-control."+chosenPaymentMethod);
        this.validated = false;
    }

    confirmationListener() {
        this.addInputFieldsToArray();
        this.validate();

        if (this.container.every(inputField => inputField.classList.contains("validated"))) {
            const chance = this.isPaymentAccepted();
            console.log(chance);
            if (chance) {
                document.querySelector("#purchase-submit").setAttribute("onclick", location.href="/confirmation");
            } else {
                alert("You gave wrong credentials or don't have enough money on your account");
                window.location.href = "/";
            }
        }
    }

    isPaymentAccepted() {
        const randomNumber = Math.floor(Math.random() * 100);
        console.log(randomNumber);
        return randomNumber > 20;
    }

     addInputFieldsToArray() {
         this.container = [];
         this.inputFields.forEach(input => this.container.push(input));
     }


    validate() {
        this.inputFields
            .forEach(input => {
                if (!input.checkValidity()) {
                    input.reportValidity();
                } else {
                    input.classList.add("validated");
                }
            });
    }

}


class PaymentMethodChooser {

    constructor() {
        this.checkboxes = document.querySelectorAll(".payment-checkbox");
        this.paymentForms = document.querySelectorAll(".payment-method-form");
        this.wrapper = document.querySelector("#wrapper");
        this.chosenPaymentMethod = undefined;
        this.inputValidator = null;
    }

    choosePaymentMethodListener() {
        this.checkboxes.forEach(checkbox => checkbox
            .addEventListener("click", event => this.choosePaymentMethodHandler(event)))
    }


    choosePaymentMethodHandler(event) {
        let target = event.target;
        target.classList.toggle("payment-method-checked");
        if (target.classList.contains("payment-method-checked")) {
            this.extracted(target, this.displayPaymentFormWhenPaymentMethodCheckedIn.bind(this));
        } else {
            this.extracted(target, this.hidePaymentFormWhenPaymentMethodCheckedOut.bind(this));
        }

        this.chosenPaymentMethod = target.id;
        this.getInstanceOfInputValidator(this.chosenPaymentMethod);
    }



    getInstanceOfInputValidator(chosenPaymentMethod) {
        this.inputValidator = new InputValidator(chosenPaymentMethod);
    }

    getInputValidator () {
        return this.inputValidator;
    }


    extracted(target, callback) {
        this.checkboxes.forEach(checkbox => {
            // hide the not checked payment method
            if (!checkbox.isEqualNode(target)) {
                checkbox
                    .parentElement
                    .parentElement
                    .parentElement
                    .classList.toggle("payment-method-hidden");
            }
            if (target.classList.contains("credit-card")) {
                callback("credit-card");
            }

            if (target.classList.contains("pay-pal")) {
                callback("pay-pal");
            }
        })
    }


    displayPaymentFormWhenPaymentMethodCheckedIn(checkedPaymentMethod) {
        this.paymentForms.forEach(form => {
                if (form.classList.contains(checkedPaymentMethod)) {
                    form.classList.remove("payment-method-form-hidden")

                } else {
                    form.remove();
                }
            }
        );
    }


    hidePaymentFormWhenPaymentMethodCheckedOut(checkedPaymentMethod) {
        this.paymentForms.forEach(form => {
            if (form.classList.contains(checkedPaymentMethod)) {
                form.classList.add("payment-method-form-hidden")
            } else {
                this.wrapper.appendChild(form);
            }
        });
    }


}

window.onload = Main.init;