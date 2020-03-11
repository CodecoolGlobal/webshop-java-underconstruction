import {AddToCartButtons} from "./cart.js";
import {filterInterface} from "./filters.js";
import {registration} from "./registration.js";

class Main {
    static init() {
        const addCardButtons = new AddToCartButtons();
        addCardButtons.addToCartListener();
        filterInterface.init();
        registration.init();
    }
}

window.onload = Main.init;