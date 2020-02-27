package com.codecool.shop.sql;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.data.sql.ConnectionProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductCategoryDaoJdbcTest {

    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();

    @BeforeAll
    @Test
    static void setConnection() {
        ConnectionProperties.readFrom("./src/test/resources/test_connection.properties");
    }
    @Test
    void testGetAll() {
        assertDoesNotThrow(() -> productCategoryDao.getAll());
        assertNotNull(productCategoryDao.getAll());
    }


}
