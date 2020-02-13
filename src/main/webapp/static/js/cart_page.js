import {CartEditor} from "./cart.js";

class Main {
    static init() {
        const cartEditor = new CartEditor();
        cartEditor.addEditListener();
    }
}

window.onload = Main.init;

