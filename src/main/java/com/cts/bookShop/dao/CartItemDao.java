package com.cts.bookShop.dao;

import com.cts.bookShop.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemDao  extends CrudRepository<CartItem,Integer> {
}
