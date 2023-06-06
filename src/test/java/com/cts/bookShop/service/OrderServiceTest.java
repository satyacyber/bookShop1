package com.cts.bookShop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.bookShop.dao.CartDao;
import com.cts.bookShop.dao.OrderDao;
import com.cts.bookShop.dao.ProductDao;
import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.CartItem;
import com.cts.bookShop.entity.OrderData;
import com.cts.bookShop.entity.Product;
import com.cts.bookShop.entity.User;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private CartDao cartDao;

    @MockBean
    private CartService cartService;

    @MockBean
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    @MockBean
    private ProductDao productDao;

    @MockBean
    private UserDao userDao;

    /**
     * Method under test: {@link OrderService#makeOrder()}
     */
    @Test
    void testMakeOrder() {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setCartItems(new ArrayList<>());
        cart.setUser(user);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Cart cart2 = new Cart();
        cart2.setCartId(1);
        cart2.setCartItems(new ArrayList<>());
        cart2.setUser(user2);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");

        OrderData orderData = new OrderData();
        orderData.setAmount(10.0d);
        orderData.setCart(cart2);
        orderData.setId(1);
        orderData.setUser(user3);
        when(orderDao.save(Mockito.<OrderData>any())).thenReturn(orderData);

        User user4 = new User();
        user4.setEmailId("42");
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setUserId(1);
        user4.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user4);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        OrderData actualMakeOrderResult = orderService.makeOrder();
        assertEquals(0.0d, actualMakeOrderResult.getAmount().doubleValue());
        assertEquals(user, actualMakeOrderResult.getUser());
        assertEquals(cart2, actualMakeOrderResult.getCart());
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(orderDao).save(Mockito.<OrderData>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OrderService#makeOrder()}
     */
    @Test
    void testMakeOrder2() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);

        CartItem cartItem = new CartItem();
        cartItem.setCust_quantity(1);
        cartItem.setId(1);
        cartItem.setProduct(product);

        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setCartItems(cartItems);
        cart.setUser(user);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Cart cart2 = new Cart();
        cart2.setCartId(1);
        cart2.setCartItems(new ArrayList<>());
        cart2.setUser(user2);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");

        OrderData orderData = new OrderData();
        orderData.setAmount(10.0d);
        orderData.setCart(cart2);
        orderData.setId(1);
        orderData.setUser(user3);
        when(orderDao.save(Mockito.<OrderData>any())).thenReturn(orderData);

        Product product2 = new Product();
        product2.setAuthor("JaneDoe");
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setISBN("ISBN");
        product2.setImgUrl("https://example.org/example");
        product2.setName("Name");
        product2.setPId(1);
        product2.setPrice(10.0d);
        product2.setQuantity(1);
        when(productDao.save(Mockito.<Product>any())).thenReturn(product2);

        User user4 = new User();
        user4.setEmailId("42");
        user4.setPassword("iloveyou");
        user4.setRole("Role");
        user4.setUserId(1);
        user4.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user4);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        OrderData actualMakeOrderResult = orderService.makeOrder();
        assertEquals(10.0d, actualMakeOrderResult.getAmount().doubleValue());
        assertEquals(user, actualMakeOrderResult.getUser());
        Cart cart3 = actualMakeOrderResult.getCart();
        assertSame(cart, cart3);
        assertEquals(0, cart3.getCartItems().get(0).getProduct().getQuantity());
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(orderDao).save(Mockito.<OrderData>any());
        verify(productDao).save(Mockito.<Product>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }
}

