import {ApiConnector} from "./api_connector.js";

export class AddToCartButtons {
    constructor() {
        this.buttons = document.querySelectorAll(".add-item");
        this.container = document.querySelector(".badge")
    }

    addToCartListener() {
        this.buttons.forEach(button => button
            .addEventListener("click", evt => this.addToCartHandler(evt)));
    }

    addToCartHandler(event) {
        const productId = event.target.dataset.productId;
        ApiConnector._api_put(`/cart?productId=${productId}&quantity=${-1}`,
                                null,
                                data => this.processJson(data));
    }

    processJson(data) {
        this.container.innerText = data["itemsTotal"];
    }
}

export class CartEditor {
    constructor() {
        this.cartRows = document.querySelectorAll(".data-row")
    }
    addEditListener() {
        this.cartRows.forEach(row => row.querySelector(".quantity")
            .addEventListener("dblclick", evt => this.provideInputField(evt)))
    }

    provideInputField(evt) {
        if (evt.target.nodeName !== "INPUT") {
            let inputContainer = evt.currentTarget;
            let originalQuantity = inputContainer.innerText;
            let productId = inputContainer.dataset.productId;
            inputContainer.innerText = "";
            let inputField = document.createElement("input");

            inputField.setAttribute("data-product-id", productId);
            inputField.setAttribute("data-original-quantity", originalQuantity);
            inputField.setAttribute("value", originalQuantity);
            inputField.classList.add("form-control", "w-25", "h-50");
            inputContainer.addEventListener("keyup", evt1 => this.handleQuantityChange(evt1));

            inputContainer.appendChild(inputField);
            inputField.focus();
        }
    }

    handleQuantityChange(event) {

        if (event.key === "Enter" || event.key === "Escape") {

            let target = event.target;
            let originalQuantity = parseInt(target.dataset.originalQuantity);
            let productId = target.dataset.productId;
            let quantityContainer = target.parentElement;
            let row = quantityContainer.parentElement;
            let unitPrice = parseFloat(row.querySelector(".row-total").dataset.rowTotal);
            let inputValue = parseInt(target.value);

            if (inputValue >= 0 && event.key === "Enter" && originalQuantity !== inputValue && inputValue !== null) {
                ApiConnector._api_put(`/cart?productId=${productId}&quantity=${inputValue}`,
                    null,
                    data => this.processJson(data));

                if (inputValue === 0) quantityContainer.parentElement.remove();
                else {
                    quantityContainer.innerText = inputValue.toString();
                    row.querySelector(".row-total").textContent = (unitPrice * inputValue).toFixed(2) + " USD";
                }

            } else {
                quantityContainer.innerText = originalQuantity.toString();
            }
        }
    }

    processJson(data) {
        console.log(data["priceTotal"]);
        console.log(data);
        document.querySelector("#cart-value").textContent = data["priceTotal"] + " USD";

    }
}
