package com.cts.bookShop.dao;


import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {
    Cart findByUser(User user);
}
