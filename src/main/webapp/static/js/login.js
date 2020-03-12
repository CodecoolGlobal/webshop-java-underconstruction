import {modalCloser} from "./modals.js";

export const login = {
    init: function () {
        const closer = Object.create(modalCloser);
        closer.setAction("login");
        closer.init();
    }
};