package com.cts.bookShop.controller;


import com.cts.bookShop.entity.OrderData;
import com.cts.bookShop.entity.Product;
import com.cts.bookShop.exception.InvalidIdException;
import com.cts.bookShop.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("/order")

    @CrossOrigin(origins="http://localhost:3000")
        public OrderData getOrder(){
        logger.info("Making an order");
        return orderService.makeOrder();
        }

}
