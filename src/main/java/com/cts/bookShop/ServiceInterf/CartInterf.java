package com.cts.bookShop.ServiceInterf;

import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartInterf {
    Cart addToCart(Integer productId);

     void removeFromCart(Integer cartItemId);

     List<CartItem> getAllCartItems();



}
