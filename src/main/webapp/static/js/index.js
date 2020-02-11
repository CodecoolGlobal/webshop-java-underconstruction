import {ApiConnector} from "./api_connector.js";
import {Util} from "./util.js";

class Main {
    static init() {
        this.productNode = document.querySelector(".product-node").cloneNode(true);
        const productCategoryFilter = new ProductCategoryFilter("product-category-filter-form");
        productCategoryFilter.buttonHandler = productCategoryFilter.handleButtonClick;
    }
}

class Filter {
    constructor(formId) {
        this.form = document.getElementById(formId);
        this.selectElement = this.form.querySelector("select");
        this.button = this.form.querySelector("button");
    }

    get selectedId() {
        const selectedId = this.selectElement.value;
        if (Util.isNumber(selectedId)) {
            return Number.parseInt(selectedId);
        } else {
            return null;
        }
    }

    set buttonHandler(handler) {
        this.button.addEventListener("click", handler.bind(this));
    }
}

class ProductCategoryFilter extends Filter {
    constructor(formId) {
        super(formId);
    }

    handleButtonClick() {
        const selectedId = this.selectedId;
        console.log("This will filter products by product category");
        console.log("Selected id: " + selectedId);
    }
}

window.onload = Main.init;