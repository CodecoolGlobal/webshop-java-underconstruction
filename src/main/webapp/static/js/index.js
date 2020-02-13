import {AddToCartButtons} from "./cart.js";
import {filterInterface} from "./filters.js";

class Main {
    static init() {
        const addCardButtons = new AddToCartButtons();
        addCardButtons.addToCartListener();
        filterInterface.init();
    }
}

window.onload = Main.init;