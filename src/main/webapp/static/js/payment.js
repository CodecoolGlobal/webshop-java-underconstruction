
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
        this.wrapper = document.querySelector("#wrapper");


    }

    choosePaymentMethodListener() {
        this.checkboxes.forEach(checkbox => checkbox
            .addEventListener("click", event => this.choosePaymentMethodHandler(event)))
    }


    // IT IS REFACTORED, BUT LOGIC IS NYAKATEKERT
    choosePaymentMethodHandler(event) {
        let target = event.target;
        target.classList.toggle("payment-method-not-checked");
        //payment method is checked
        if (!target.classList.contains("payment-method-not-checked")) {
            this.extracted(target, this.displayPaymentFormWhenPaymentMethodCheckedIn.bind(this));
        } else {
            this.extracted(target, this.hidePaymentFormWhenPaymentMethodCheckedOut.bind(this));
        }
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