export class CardView {
    constructor(template, product) {
        this.template = template.cloneNode(true);
        this.imageById = product.id;
        this.name = product.name;
        this.description = product.description;
        const price = parseFloat(product.defaultPrice).toFixed(1);
        this.price = `${price} ${product.defaultCurrency}`;
    }

    set imageById(id) {
        this.template.querySelector(".product-image").src = `/static/img/product_${id}.jpg`;
    }

    set name(name) {
        this.template.querySelector(".product-name").textContent = name;
    }

    set description(description) {
        this.template.querySelector(".product-description").textContent = description;
    }

    set price(price) {
        this.template.querySelector(".product-price").textContent = price;
    }

    render(container) {
        container.appendChild(this.template);
    }
}