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
            let target = evt.currentTarget;
            let originalQuantity = target.firstElementChild.innerText;
            let productId = target.dataset.productId;
            let inputContainer = target.firstElementChild;
            inputContainer.innerHTML = "";
            let inputField = document.createElement("input");

            inputField.setAttribute("data-product-id", productId);
            inputField.setAttribute("data-original-quantity", originalQuantity);
            inputField.setAttribute("value", originalQuantity);
            inputField.classList.add("form-control", "w-25", "h-50", "autofocus");
            inputField.addEventListener("keyup", evt1 => this.handleQuantityChange(evt1));
            inputField.addEventListener("blur", evt2 => this.handleQuantityChange(evt2));

            inputContainer.appendChild(inputField);
            inputField.focus();
        }
    }

    handleQuantityChange(evt1) {
        let target = evt1.target;
        let inputValue = parseInt(target.value);
        let productId = target.dataset.productId;
        let originalQuantity = parseInt(target.dataset.originalQuantity);

        let quantityContainer = target.parentElement;
        let quantityField = document.createElement('p');

        if (inputValue >= 0 && evt1.key === "Enter" && originalQuantity !== inputValue) {
            ApiConnector._api_put(`/cart?productId=${productId}&quantity=${inputValue}`,
                                    null,
                                    data => this.processJson(data));
            quantityContainer.innerHTML = "";
            quantityField.innerHTML = inputValue.toString();

        }
        else if (evt1.type === "blur" || evt1.key === "Enter") {
            quantityContainer.innerHTML = "";
            quantityField.innerHTML = originalQuantity.toString();

        }
        else if (evt1.key === "Escape") {
            target.blur();
        }
        quantityContainer.appendChild(quantityField);
    }

    processJson(data) {
        console.log(data["priceTotal"]);

    }


}
