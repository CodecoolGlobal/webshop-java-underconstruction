import {FilterProvider} from "./filters.js";
import {AddToCartButtons} from "./cart.js";

class Main {
    static init() {
        const filterProvider = new FilterProvider();
        const productCategoryFilter = filterProvider.productCategoryFilter;
        productCategoryFilter.buttonHandler = productCategoryFilter.handleButtonClick;
        const supplierFilter = filterProvider.supplierFilter;
        supplierFilter.buttonHandler = supplierFilter.handleButtonClick;
        const addCardButtons = new AddToCartButtons();
        addCardButtons.addToCartListener();
    }
}

window.onload = Main.init;