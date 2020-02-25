
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
        target.classList.toggle("payment-method-not-checked");
        if (!target.classList.contains("payment-method-not-checked")) {
            this.checkboxes.forEach(checkbox => {
                if (!checkbox.isEqualNode(target)) {
                    checkbox
                        .parentElement
                        .parentElement
                        .parentElement
                        .classList.add("payment-method-hidden");
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
            })
        }
    }
}

window.onload = Main.init;