package com.cts.bookShop.controller;


import com.cts.bookShop.entity.OrderData;
import com.cts.bookShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order")
        public OrderData getOrder(){
            return orderService.makeOrder();
        }

}
