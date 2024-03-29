package com.cts.bookShop.service;

import com.cts.bookShop.ServiceInterf.CartInterf;
import com.cts.bookShop.controller.UserController;
import com.cts.bookShop.dao.CartDao;
import com.cts.bookShop.dao.CartItemDao;
import com.cts.bookShop.dao.ProductDao;
import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.CartItem;
import com.cts.bookShop.entity.Product;
import com.cts.bookShop.entity.User;
import com.cts.bookShop.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.cts.bookShop.controller.UserController.CURRENT_USER;

@Service
public class CartService implements CartInterf {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
     @Autowired
    private UserDao userDao;
    @Autowired
    private CartItemDao cartItemDao;



    public Cart addToCart(Integer productId){
        User user = userDao.findByUserName(CURRENT_USER).get();
        Product prod= productDao.findById(productId).get();
        Cart exist=null;
        exist=cartDao.findByUser(user);
        if(exist!=null){
            final int flag[]= new int[1];//to check if the product exist in the cart or not
            exist.getCartItems().stream().forEach(e->{
                if(e.getProduct().getPId()==productId){
                    e.setCust_quantity(e.getCust_quantity()+1);
                    flag[0]=1;
                }

            });
            cartDao.save(exist);

            //System.out.println(flag[0]);
            if(flag[0]==0){
                CartItem item= new CartItem(prod,1);
                cartItemDao.save(item);
                exist.getCartItems().add(item);
                cartDao.save(exist);
            }
            return exist;
        }

        CartItem item= new CartItem(prod,1);
        cartItemDao.save(item);
        List<CartItem> l=new ArrayList<>();
        l.add(item);
        Cart cart= new Cart(l,user);
        cartDao.save(cart);
        return cart;


    }

    public void removeFromCart(Integer cartItemId) {
        String uname= CURRENT_USER;
        User user = userDao.findByUserName(CURRENT_USER).get();
        Cart cart = cartDao.findByUser(user);

        cart.getCartItems().removeIf(cartItem -> cartItem.getProduct().getPId()==cartItemId);
        cartDao.save(cart);

    }

    public List<CartItem> getAllCartItems() {
        User user = userDao.findByUserName(CURRENT_USER).get();
        Cart cart = cartDao.findByUser(user);
        if (cart != null) {
            return cart.getCartItems();
        }
        else{
            throw new ResourceNotFoundException("Cart not found");

        }
//        return new ArrayList<>();
    }



}
