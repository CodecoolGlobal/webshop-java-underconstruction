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
