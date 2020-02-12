export class Util {

    static isNumber(data) {
        return !isNaN(data);
    }

    static get queryString() {
        return new QueryString();
    }
}

class QueryString {
    constructor() {
        this.data = "";
    }

    extendWith(param) {
        if (this.data === "") {
            this.data += `?${param}`;
        } else {
            this.data += `&&${param}`;
        }
    }

    hasParams() {
        return this.data !== "";
    }

    toString() {
        return this.data;
    }
}