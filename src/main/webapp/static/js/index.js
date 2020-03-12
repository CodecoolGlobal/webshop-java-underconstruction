import {AddToCartButtons} from "./cart.js";
import {filterInterface} from "./filters.js";
import {registration} from "./registration.js";
import {login} from "./login.js";

class Main {
    static init() {
        const addCardButtons = new AddToCartButtons();
        addCardButtons.addToCartListener();
        filterInterface.init();
        registration.init();
        login.init();
    }
}

window.onload = Main.init;