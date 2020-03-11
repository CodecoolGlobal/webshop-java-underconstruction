import {ApiConnector} from "./api_connector.js";


class Main {
    static init() {
        ApiConnector._api_get("/confirmation/customer",
            data => {
            console.log(data);
            })
    }



}


class EmailSender {



}


window.onload = Main.init;