package com.codecool.shop.data.factories;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;

public class SupplierFactory extends Factory {

    private static Factory self = new SupplierFactory();
    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    private SupplierFactory() {}

    public static Factory getInstance() {
        return self;
    }

    @Override
    protected void synthesize(String[] entries) {
        int id = Integer.parseInt(entries[0]);
        String name = entries[1];
        String description = entries[2];
        Supplier supplier = new Supplier(name, description);
        supplier.setId(id);
        supplierDataStore.add(supplier);
    }
}
