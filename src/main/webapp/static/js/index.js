import {FilterProvider} from "./filters.js";

class Main {
    static init() {
        const filterProvider = new FilterProvider();
        const productCategoryFilter = filterProvider.productCategoryFilter;
        productCategoryFilter.buttonHandler = productCategoryFilter.handleButtonClick;
    }
}

window.onload = Main.init;