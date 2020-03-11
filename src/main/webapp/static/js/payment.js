import {ApiConnector} from "./api_connector.js";


class Main {
    static init() {
        const paymentMethodChooser = new PaymentMethodChooser();
        paymentMethodChooser.choosePaymentMethodListener();



        document.querySelector("#purchase-submit")
            .addEventListener("click", () => {
                inputValidator.confirmationListener();
            });


    }
}

class InputValidator {

    constructor(chosenPaymentMethod) {
        this.inputFields = document.querySelectorAll(".form-control credit-card");
        console.log(this.inputFields);
        this.validated = false;
        this.paymentMethod = chosenPaymentMethod;
        console.log(this.paymentMethod);


    }

    confirmationListener() {
        this.container = [];
        this.inputFields.forEach(input => this.container.push(input));
        console.log(this.container);

        this.validate();


        if (this.container.every(inputField => inputField.classList.contains("validated"))) {
            console.log("every field is validated");
        }


        /*                if (validInputs) {
                            ApiConnector._api_post("/confirmation", , resp => {
                                console.log(resp.result);
                            });
                }*/
            }

    get inputsValid() {
        return this.validated;
    }

    set inputsValid (validity) {
        this.validated = validity;
    }

    validate() {
        this.inputFields
            .forEach(input => {
                if (!input.checkValidity()) {
                    input.reportValidity();
                } else {
                    input.classList.add("validated");
                    // input.classList.add("validated");

                }


            });
    }

    directToPaymentProvider() {
        this.inputFields.forEach(input => {

        })
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