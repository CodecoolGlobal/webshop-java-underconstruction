package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.SupplierDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.util.SQLDumpReader;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private static ProductDao productDao;
    private static ProductCategoryDao categoryDao;
    private static SupplierDao supplierDao;
    private static Executor executor;

    @BeforeAll
    static void init() {
        initDao(ProductDaoJDBC.class);
    }

    static void initDao(Class<? extends ProductDao> daoClass) {
        if (daoClass == ProductDaoMem.class) {
            productDao = ProductDaoMem.getInstance();
            categoryDao = ProductCategoryDaoMem.getInstance();
            supplierDao = SupplierDaoMem.getInstance();
        } else {
            ConnectionProperties.readFrom("./src/test/resources/test_connection.properties");
            productDao = new ProductDaoJDBC();
            categoryDao = new ProductCategoryDaoJDBC();
            supplierDao = new SupplierDaoJDBC();
            executor = new Executor();
            cleanse();
        }
    }

    static void cleanse() {
        if (productDao instanceof ProductDaoJDBC) {
            String query = SQLDumpReader.getQueryString();
            StatementProvider statementProvider = connection -> connection.prepareStatement(query);
            executor.execute(statementProvider);
        } else {
            productDao.remove(2);
            categoryDao.remove(2);
            supplierDao.remove(2);
        }
    }

    @BeforeEach
    void cleanseBeforeEach() {
        cleanse();
        ProductCategory category = createProductCategory();
        Supplier supplier = createSupplier();
        categoryDao.add(category);
        supplierDao.add(supplier);
    }

    @AfterAll
    static void cleanseDatabaseAfterAll() {
        cleanse();
    }

    @Test
    void testAdd() {
        Product product = createProduct(categoryDao.find(2), supplierDao.find(2));
        assertDoesNotThrow(() -> productDao.add(product));
        assertTrue(productDao.getBy(product.getSupplier()).stream().anyMatch(product1 -> {
            return product1.getName().equals(product.getName()) && product1.getSupplier().getName().equals(product.getSupplier().getName());
        }));
    }

    @Test
    void testFind() {
        Product product = createProduct(categoryDao.find(2), supplierDao.find(2));
        productDao.add(product);
        Product result = productDao.find(2);
        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void testRemove() {
        Product newProduct = createProduct(categoryDao.find(2), supplierDao.find(2));
        productDao.add(newProduct);
        assertDoesNotThrow(() -> productDao.remove(2));
        assertNull(productDao.find(2));
    }

    @Test
    void testGetAllReturnsNotNull() {
        List<Product> products = productDao.getAll();
        assertNotNull(products, "Was null");
    }

    @Test
    void testGetByProductCategory() {
        ProductCategory category = categoryDao.find(2);
        Product newProduct = createProduct(category, supplierDao.find(2));
        productDao.add(newProduct);
        List<Product> products = productDao.getBy(category);
        assertTrue(products.stream().anyMatch(p -> p.getName().equals(newProduct.getName()) &&
                p.getProductCategory().getName().equals(newProduct.getProductCategory().getName())));
    }

    @Test
    void testGetBySupplier() {
        Supplier supplier = supplierDao.find(2);
        Product newProduct = createProduct(categoryDao.find(2), supplier);
        productDao.add(newProduct);
        List<Product> products = productDao.getBy(supplier);
        assertTrue(products.stream().anyMatch(p -> p.getName().equals(newProduct.getName()) &&
                p.getSupplier().getName().equals(newProduct.getSupplier().getName())));
    }

    @AfterAll
    @Test
    static void finalCheck() {
        assertTrue(productDao.getAll().isEmpty());
    }


    static ProductCategory createProductCategory() {
        return new ProductCategory(2, "cat", "dep", "desc");
    }

    static Supplier createSupplier() {
        return new Supplier(2, "sup", "desc");
    }

    static Product createProduct(ProductCategory category, Supplier supplier) {
        return new Product(2, "p", 1.0f, "USD", "desc", category, supplier);
    }

}