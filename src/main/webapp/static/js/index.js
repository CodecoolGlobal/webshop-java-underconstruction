import {ApiConnector} from "./api_connector.js";
import {Util} from "./util.js";

class Main {
    static init() {
        const productCategoryFilter = new ProductCategoryFilter("product-category-filter-form");
        productCategoryFilter.buttonHandler = productCategoryFilter.handleButtonClick;
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
        const selectedId = this.selectElement.dataset.selectedCategoryId;
        return (selectedId === "null") ? -1 : Number.parseInt(selectedId);
    }

    set selectedId(id) {
        this.selectElement.dataset.selectedCategoryId = id;
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

class CardView {
    constructor(template, product) {
        this.template = template.cloneNode(true);
        this.imageById = product.id;
        this.name = product.name;
        this.description = product.description;
        const price = parseFloat(product.defaultPrice).toFixed(1);
        this.price = `${price} ${product.defaultCurrency}`;
    }

    set imageById(id) {
        this.template.querySelector(".product-image").src = `/static/img/product_${id}.jpg`;
    }

    set name(name) {
        this.template.querySelector(".product-name").textContent = name;
    }

    set description(description) {
        this.template.querySelector(".product-description").textContent = description;
    }

    set price(price) {
        this.template.querySelector(".product-price").textContent = price;
    }

    render(container) {
        container.appendChild(this.template);
    }
}

window.onload = Main.init;