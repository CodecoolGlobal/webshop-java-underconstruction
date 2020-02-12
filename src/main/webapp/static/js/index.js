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
        if (selectedId != null) {
            ApiConnector._api_get(`/?product_category=${selectedId}`, products => {
                products.forEach(p => console.log(p.name));
            })
        }
    }
}

window.onload = Main.init;