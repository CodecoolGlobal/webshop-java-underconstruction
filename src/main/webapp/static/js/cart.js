import {ApiConnector} from "./api_connector.js";

export class CartIcon {
    constructor() {
    }
}

export class AddToCartButtons {
    constructor() {
        this.buttons = document.querySelectorAll(".add-item");
    }

    addToCartListener() {
        this.buttons.forEach(button => button
            .addEventListener("click", evt => this.addToCartHandler(evt)));
    }

    addToCartHandler(event) {
        const productId = event.target.dataset.productId;
        console.log(productId);
        ApiConnector._api_put(`/cart?productId=${productId}&quantity=${-1}`,
                                null,
                                data => this.processJson(data));
    }

    processJson(data) {
        console.log(data["priceTotal"]);
    }
}

export class CartReview {
    constructor() {
    }
}

class Order {
    constructor() {
        this.order = null;
    }

}