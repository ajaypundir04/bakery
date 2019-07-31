package com.hexad.bakery;

import com.hexad.bakery.constants.ApplicationConstant;
import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.service.BakeryService;
import com.hexad.bakery.util.FileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * Intial Point of Application
 */
@SpringBootApplication
@EnableJpaRepositories
public class ApplicationLauncher {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationLauncher.class, args);
        applicationContext.registerShutdownHook();

        BakeryService bakeryService = applicationContext.getBean(BakeryService.class);

        List<Order> orders = FileReader.loadOrder("orders.json", ApplicationConstant.JSON_EXTENSION);
        Invoice invoice = bakeryService.processOrder(orders);
        invoice.prettyPrint();
        applicationContext.close();
    }

}
