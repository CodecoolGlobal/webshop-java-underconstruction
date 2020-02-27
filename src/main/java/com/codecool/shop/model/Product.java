package com.codecool.shop.model;


import com.codecool.shop.controller.requestprocessing.filtering.FilterProductBy;
import com.codecool.shop.controller.requestprocessing.filtering.ProductFilterFieldMap;
import com.google.gson.annotations.Expose;

import java.util.Currency;

public class Product extends BaseModel implements Filterable {

    @Expose
    private float defaultPrice;
    @Expose
    private Currency defaultCurrency;
    @Expose
    @FilterProductBy(requestParameterName = "product_category")
    private ProductCategory productCategory;
    @Expose
    @FilterProductBy(requestParameterName = "supplier")
    private Supplier supplier;
    private ProductFilterFieldMap filterFieldMap;


    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        this.filterFieldMap = new ProductFilterFieldMap(this);
    }

    public Product(int id, String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(id, name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        this.filterFieldMap = new ProductFilterFieldMap(this);
    }


    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public float getRawPrice() { return this.defaultPrice; }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public ProductFilterFieldMap getFilterFieldMap() {
        return filterFieldMap;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        if (Float.compare(product.defaultPrice, defaultPrice) != 0) return false;
        if (defaultCurrency != null ? !defaultCurrency.equals(product.defaultCurrency) : product.defaultCurrency != null)
            return false;
        if (productCategory != null ? !productCategory.equals(product.productCategory) : product.productCategory != null)
            return false;
        return supplier != null ? supplier.equals(product.supplier) : product.supplier == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (defaultPrice != +0.0f ? Float.floatToIntBits(defaultPrice) : 0);
        result = 31 * result + (defaultCurrency != null ? defaultCurrency.hashCode() : 0);
        result = 31 * result + (productCategory != null ? productCategory.hashCode() : 0);
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        return result;
    }
}
