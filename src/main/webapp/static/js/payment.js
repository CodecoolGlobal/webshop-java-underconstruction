
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
                    this.paymentForms.forEach(form => {
                        if (form.classList.contains("credit-card")) {
                            form.classList.remove("payment-method-form-hidden")

                        }

                    }
                    );
                }

                if (target.classList.contains("pay-pal")) {
                    this.paymentForms.forEach(form => {
                            if (form.classList.contains("pay-pal")) {
                                form.classList.remove("payment-method-form-hidden")
                            } else  {
                                form.remove();
                            }
                        }
                    );
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
                    this.paymentForms.forEach(form => {
                        if (form.classList.contains("credit-card"))
                        form.classList.add("payment-method-form-hidden")
                    });
                }

                if (target.classList.contains("pay-pal")) {
                    this.paymentForms.forEach(form => {
                        if (form.classList.contains("pay-pal")) {
                            form.classList.add("payment-method-form-hidden")
                        } else {
                            this.wrapper.appendChild(form);
                        }
                    });
                }


            })
        }
    }
}

window.onload = Main.init;