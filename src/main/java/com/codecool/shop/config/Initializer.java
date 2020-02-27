package com.codecool.shop.config;

import com.codecool.shop.data.DaoBuilder;
import com.codecool.shop.data.sql.ConnectionProperties;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionProperties.readFrom("./src/main/resources/connection.properties");

        // --------------------
        // Using Memory Dao:
        // --------------------

//        try {
//            DaoBuilder.extractData(sce.getServletContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

            // for testing purposes only
           /* ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
            SupplierDao supplierDao = new SupplierDaoJDBC();
            Product product = new Product(
                    "Incilius Alvarius Toad",
                    (float)27.89,
                    "USD",
                    "It secretes a potent hallucinogenic compound from glands on either side of its head. You can dry and smoke the compound and get a short-lived but intense psychedelic experience thanks to the potent chemicals 5-MeO-DMT and bufotenin.",
                    productCategoryDao.find(1),
                    supplierDao.find(1));

            ProductDao productDao = new ProductDaoJDBC();
            productDao.add(product);*/
            // end testing purpose

      /*  } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
