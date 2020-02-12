import {Util} from "./util.js";
import {ApiConnector} from "./api_connector.js";
import {CardView} from "./dom.js";

export class FilterProvider {

    constructor() {
        this.productCategoryFilterInstance = new ProductCategoryFilter("product-category-filter-form");
        this.supplierFilterInstance = new SupplierFilter("supplier-filter");
    }

    get productCategoryFilter() {
        return this.productCategoryFilterInstance;
    }

    get supplierFilter() {
        return this.supplierFilterInstance;
    }
}

class Filter {
    constructor(formId) {
        this.productListContainer = document.getElementById("products");
        this.productCardViewTemplate = document.querySelector(".product-card-view").cloneNode(true);
        this.form = document.getElementById(formId);
        this.selectElement = this.form.querySelector("select");
        this.button = this.form.querySelector("button");
    }

    get previouslySelectedId() {
        const selectedId = this.selectElement.dataset.selectedId;
        return Number.parseInt(selectedId);
    }

    set selectedId(id) {
        this.selectElement.dataset.selectedId = id;
    }

    get selectedId() {
        const selectedId = this.selectElement.value;
        if (Util.isNumber(selectedId)) {
            return Number.parseInt(selectedId);
        } else {
            return -1;
        }
    }


    set buttonHandler(handler) {
        this.button.addEventListener("click", handler.bind(this));
    }

    clearContainer() {
        this.productListContainer.innerHTML = "";
    }

    processResponse(products) {
        const cardViews = products.map(p => new CardView(this.productCardViewTemplate, p));
        this.clearContainer();
        cardViews.forEach(cardView => cardView.render(this.productListContainer));
    }
}

class ProductCategoryFilter extends Filter {

    constructor(formId) {
        super(formId);
    }

    handleButtonClick() {
        const selectedId = this.selectedId;
        if (selectedId !== this.previouslySelectedId) {
            ApiConnector._api_get(
                `/?product_category=${selectedId}`,
                products => this.processResponse(products)
            );
            this.selectedId = selectedId;
        }
    }
}

class SupplierFilter extends Filter {
    constructor(formId) {
        super(formId);
    }

    handleButtonClick() {
        const selectedId = this.selectedId;
        if (selectedId !== this.previouslySelectedId) {
            ApiConnector._api_get(
                `/?supplier=${selectedId}`,
                products => this.processResponse(products)
            );
            this.selectedId = selectedId;
        }
    }
}