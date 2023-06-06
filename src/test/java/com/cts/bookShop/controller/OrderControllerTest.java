package com.cts.bookShop.controller;

import static org.mockito.Mockito.when;

import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.OrderData;
import com.cts.bookShop.entity.User;
import com.cts.bookShop.service.OrderService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    /**
     * Method under test: {@link OrderController#getOrder()}
     */
    @Test
    void testGetOrder() throws Exception {
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

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");

        OrderData orderData = new OrderData();
        orderData.setAmount(10.0d);
        orderData.setCart(cart);
        orderData.setId(1);
        orderData.setUser(user2);
        when(orderService.makeOrder()).thenReturn(orderData);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"user\":{\"userId\":1,\"emailId\":\"42\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"role\":\"Role\"},"
                                        + "\"cart\":{\"cartId\":1,\"cartItems\":[],\"user\":{\"userId\":1,\"emailId\":\"42\",\"userName\":\"janedoe\",\"password\":"
                                        + "\"iloveyou\",\"role\":\"Role\"}},\"amount\":10.0}"));
    }
}

