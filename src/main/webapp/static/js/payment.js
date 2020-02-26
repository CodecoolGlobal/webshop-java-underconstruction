
// REFACTOR
class Main {
    static init() {
        const paymentMethodChooser = new PaymentMethodChooser();
        paymentMethodChooser.choosePaymentMethodListener();
    }
}

class PaymentMethodChooser {

    constructor() {
        this.checkboxes = document.querySelectorAll(".payment-checkbox");
        this.paymentForms = document.querySelectorAll(".payment-method-form");
        console.log(this.paymentForms);
        this.wrapper = document.querySelector("#wrapper");


    }

    choosePaymentMethodListener() {
        this.checkboxes.forEach(checkbox => checkbox
            .addEventListener("click", event => this.choosePaymentMethodHandler(event)))
    }


    // REFACTOR
    choosePaymentMethodHandler(event) {
        let target = event.target;
        target.classList.toggle("payment-method-not-checked");
        //payment method is checked
        if (!target.classList.contains("payment-method-not-checked")) {
            this.checkboxes.forEach(checkbox => {
                // hide the not checked payment method
                if (!checkbox.isEqualNode(target)) {
                    checkbox
                        .parentElement
                        .parentElement
                        .parentElement
                        .classList.add("payment-method-hidden");
                }

                if (target.classList.contains("credit-card")) {
                    this.displayNeededPaymentFormWhenPaymentMethodCheckedIn("credit-card");
                }


                if (target.classList.contains("pay-pal")) {
                    this.displayNeededPaymentFormWhenPaymentMethodCheckedIn("pay-pal");
                }
            })
        } else {
            this.checkboxes.forEach(checkbox => {
                if (!checkbox.isEqualNode(target)) {
                    checkbox
                        .parentElement
                        .parentElement
                        .parentElement
                        .classList.remove("payment-method-hidden");

                }

                if (target.classList.contains("credit-card")) {
                    this.displayNeededPaymentFormWhenPaymentMethodCheckedOut("credit-card");
                }

                if (target.classList.contains("pay-pal")) {
                    this.displayNeededPaymentFormWhenPaymentMethodCheckedOut("pay-pal");
                }

            })
        }
    }



    displayNeededPaymentFormWhenPaymentMethodCheckedIn(checkedPaymentMethod) {
        this.paymentForms.forEach(form => {
                if (form.classList.contains(checkedPaymentMethod)) {
                    form.classList.remove("payment-method-form-hidden")

                } else {
                    form.remove();
                }
            }
        );
    }

    displayNeededPaymentFormWhenPaymentMethodCheckedOut(checkedPaymentMethod) {
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