import {ApiConnector} from "./api_connector.js";
import {CardView} from "./dom.js";
import {AddToCartButtons} from "./cart.js";
import {Util} from "./util.js";

export const filterInterface = {
    init() {
        new FilterService().init();
    }
};

class FilterService {
    constructor() {
        this.container = document.getElementById("filters");
        this.button = this.container.querySelector("button");
        this.productListContainer = document.getElementById("products");
        this.productCardViewTemplate = document.querySelector(".product-card-view").cloneNode(true);
        this.supplierFilter = new Filter("supplier-filter");
        this.productCategoryFilter = new Filter("product-category-filter");
    }

    init() {
        this.buttonHandler = function () {
            const supplierOption = this.supplierFilter.selectedOption;
            const categoryOption = this.productCategoryFilter.selectedOption;

            const queryString = Util.queryString;

            if (supplierOption !== this.supplierFilter.currentId || categoryOption !== this.productCategoryFilter.currentId) {
                queryString.extendWith(`supplier=${supplierOption}`);
                queryString.extendWith(`product_category=${categoryOption}`);
            }

            if (queryString.hasParams()) {
                ApiConnector._api_get(`/${queryString.toString()}`, products => this.processResponse(products));
                this.supplierFilter.currentId = supplierOption;
                this.productCategoryFilter.currentId = categoryOption;
            }
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
        const addToCartButtons = new AddToCartButtons();
        addToCartButtons.addToCartListener();
    }
}

class Filter {
    constructor(containerId) {
        this.container = document.getElementById(containerId);
        this.selectElement = this.container.querySelector("select");
    }

    get currentId() {
        return this.container.dataset.currentId;
    }

    set currentId(id) {
        this.container.dataset.currentId = id;
    }

    get selectedOption() {
        return this.selectElement.value;
    }
}