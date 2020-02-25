
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
            .addEventListener("click", function () {
                console.log("HEY");
            }))
    }
}

window.onload = Main.init;