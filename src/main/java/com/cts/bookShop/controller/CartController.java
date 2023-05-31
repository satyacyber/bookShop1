package com.cts.bookShop.controller;

import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.CartItem;
import com.cts.bookShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;



    @GetMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable Integer productId){
         return cartService.addToCart(productId);
    }

  @GetMapping("/removeFromCart/{pId}")
  public void removeFromCart(@PathVariable Integer pId){
        cartService.removeFromCart(pId);
  }
//    public ResponseEntity<String> removeFromCart(@PathVariable Integer cartItemId) {
//        cartService.removeFromCart(cartItemId);
//        return ResponseEntity.ok("Item removed from cart successfully.");
//    }

    @GetMapping("/viewCart")
    public List<CartItem> viewCart() {
        return cartService.getAllCartItems();
    }


}
