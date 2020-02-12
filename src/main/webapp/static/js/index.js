import {FilterProvider} from "./filters.js";

class Main {
    static init() {
        const filterProvider = new FilterProvider();
        const productCategoryFilter = filterProvider.productCategoryFilter;
        productCategoryFilter.buttonHandler = productCategoryFilter.handleButtonClick;
        const supplierFilter = filterProvider.supplierFilter;
        supplierFilter.buttonHandler = supplierFilter.handleButtonClick;
    }
}

window.onload = Main.init;