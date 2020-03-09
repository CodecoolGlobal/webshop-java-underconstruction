package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import com.codecool.shop.data.sql.Executor;
import com.codecool.shop.data.sql.StatementProvider;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static ProductCategoryDao productCategoryDao;
    private static Executor executor;

    @BeforeAll
    static void init() {
        initDao(ProductCategoryDaoMem.class);
    }

    private static void initDao(Class<? extends ProductCategoryDao> daoClass) {
        if (daoClass == ProductCategoryDaoJDBC.class) {
            ConnectionProperties.readFrom("./src/test/resources/test_connection.properties");
            productCategoryDao = new ProductCategoryDaoJDBC();
            executor = new Executor();
        }
        else productCategoryDao = ProductCategoryDaoMem.getInstance();
    }

    private void cleanse() {
        if (productCategoryDao instanceof ProductCategoryDaoJDBC) {
            String query = "TRUNCATE product, product_category, supplier, line_item RESTART IDENTITY;";
            StatementProvider statementProvider = connection -> connection.prepareStatement(query);
            executor.execute(statementProvider);
        } else {
            productCategoryDao.remove(1);
            productCategoryDao.remove(2);
        }
    }

    @BeforeEach
    void cleanseBeforeEach() {
        cleanse();
    }

    @Test
    void testGetAllIfNoRecord() {
        assertEquals(new ArrayList<ProductCategory>(),productCategoryDao.getAll());
    }

    @Test
    void testGetAllIfRecordsExist() {
        ProductCategory category2 = new ProductCategory(
                1, "Natural psychedelic", "Recreation", null
        );
        ProductCategory category3 = new ProductCategory(
                2, "Test stuff", "Recreation", "This is a test instance"
        );
        ProductCategory[] categories = {category2, category3};

        productCategoryDao.add(category2);
        productCategoryDao.add(category3);

        assertArrayEquals(categories, productCategoryDao.getAll().toArray());
    }

    @Test
    void testFindIfExists() {
        ProductCategory category = new ProductCategory(
                1, "Natural psychedelic", "Recreation", null
        );
        productCategoryDao.add(category);
        assertEquals(category, productCategoryDao.find(1));
    }

    @Test
    void testFindIfNotExists() {
        assertNull(productCategoryDao.find(1));
    }

    @Test
    void testRemoveIfExists() {
        ProductCategory category = new ProductCategory(
                1, "Natural psychedelic", "Recreation", null
        );
        productCategoryDao.add(category);
        assertEquals(category, productCategoryDao.find(1));
        productCategoryDao.remove(1);
        assertNull(productCategoryDao.find(1));
    }
}