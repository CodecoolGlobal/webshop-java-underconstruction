package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.sqlImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.sqlImplementation.SupplierDaoJDBC;
import com.codecool.shop.data.DaoBuilder;
import com.codecool.shop.model.Product;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            DaoBuilder.extractData(sce.getServletContext());

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
