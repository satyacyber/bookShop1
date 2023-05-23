package com.cts.bookShop.controller;

import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;



    @GetMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable Integer id){
         return cartService.addToCart(id);
    }
//    public Cart addToCart(@PathVariable(name = "productId") Integer productId){
//        return cartService.addToCart(productId);
//    }

}
