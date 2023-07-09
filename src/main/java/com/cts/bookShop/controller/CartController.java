package com.cts.bookShop.controller;

import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.CartItem;
import com.cts.bookShop.service.CartService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class CartController {
    @Autowired
        private CartService cartService;
    private static final Logger logger = LogManager.getLogger(UserController.class);


    @PostMapping("/addToCart/{productId}")
    @CrossOrigin(origins="http://localhost:3000")
    public Cart addToCart(@PathVariable Integer productId){
         logger.info("Product added to the cart");
        return cartService.addToCart(productId);
    }

  @DeleteMapping("/removeFromCart/{pId}")
  @CrossOrigin(origins="http://localhost:3000")
  public void removeFromCart(@PathVariable Integer pId){

      logger.info("Removing product from the cart: {}", pId);
      cartService.removeFromCart(pId);
  }
//    public ResponseEntity<String> removeFromCart(@PathVariable Integer cartItemId) {
//        cartService.removeFromCart(cartItemId);
//        return ResponseEntity.ok("Item removed from cart successfully.");
//    }

    @GetMapping("/viewCart")
    @CrossOrigin(origins="http://localhost:3000")
    public List<CartItem> viewCart() {
        logger.info("Viewing cart");
        return cartService.getAllCartItems();
    }


}
