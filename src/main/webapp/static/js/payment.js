
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
        console.log(this.checkboxes);
    }

    choosePaymentMethodListener() {
        this.checkboxes.forEach(checkbox => checkbox
            .addEventListener("click", event => this.choosePaymentMethodHandler(event)))
    }

    choosePaymentMethodHandler(event) {
        let target = event.target;
        console.log("HEY");
        if (!target.classList.contains("payment-method-checked")) {
            this.checkboxes.forEach(checkbox => {
                if (checkbox.isEqualNode(target)) {
                    checkbox.classList.add("payment-method-checked");
                    checkbox.classList.remove("payment-method-not-checked");
                } else {
                    // REFACTOR - TOGGLE??
                    checkbox
                        .parentElement
                        .parentElement
                        .parentElement
                        .classList.add("payment-method-hidden");
                }
            })
        } else {
            this.checkboxes.forEach(checkbox => {
                if (checkbox.isEqualNode(target)) {
                    checkbox.classList.remove("payment-method-checked");
                    checkbox.classList.add("payment-method-not-checked");
                } else {
                    // REFACTOR
                    checkbox
                        .parentElement
                        .parentElement
                        .parentElement
                        .classList.remove("payment-method-hidden");
                }
            })
        }
    }
}

window.onload = Main.init;