import {filterInterface} from "./filters.js";

class Main {
    static init() {
        filterInterface.init();
    }
}

window.onload = Main.init;