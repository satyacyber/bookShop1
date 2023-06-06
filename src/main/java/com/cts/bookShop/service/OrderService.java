package com.cts.bookShop.service;

import com.cts.bookShop.ServiceInterf.OrderInterf;
import com.cts.bookShop.controller.UserController;
import com.cts.bookShop.dao.CartDao;
import com.cts.bookShop.dao.OrderDao;
import com.cts.bookShop.dao.ProductDao;
import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderInterf {
    @Autowired
    OrderDao orderDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    CartService cartService;
    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    public OrderData makeOrder() {
        String userName;
        userName = UserController.CURRENT_USER;
        User user = userDao.findByUserName(userName).get();
        Cart cartExist = cartDao.findByUser(user);
        if (cartExist != null) {
            List<CartItem> list = cartExist.getCartItems();
            double amount = 0.0d;
            Product p;
            for (CartItem x : list) {
                amount += x.getCust_quantity() * x.getProduct().getPrice();
                p = x.getProduct();
                p.setQuantity(p.getQuantity() - 1);
                productDao.save(p);
            }
            OrderData orderData = new OrderData(user, cartExist, amount);
            orderDao.save(orderData);

            return orderData;
        }
        else {
            return  null;
        }

    }


}

