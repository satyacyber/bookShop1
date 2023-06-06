package com.cts.bookShop.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.User;
import com.cts.bookShop.service.CartService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartService cartService;

    /**
     * Method under test: {@link CartController#addToCart(Integer)}
     */
    @Test
    void testAddToCart() throws Exception {
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
        when(cartService.addToCart(Mockito.<Integer>any())).thenReturn(cart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addToCart/{productId}", 1);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"cartId\":1,\"cartItems\":[],\"user\":{\"userId\":1,\"emailId\":\"42\",\"userName\":\"janedoe\",\"password\":\"iloveyou"
                                        + "\",\"role\":\"Role\"}}"));
    }

    /**
     * Method under test: {@link CartController#removeFromCart(Integer)}
     */
    @Test
    void testRemoveFromCart() throws Exception {
        doNothing().when(cartService).removeFromCart(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/removeFromCart/{pId}", 1);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CartController#removeFromCart(Integer)}
     */
    @Test
    void testRemoveFromCart2() throws Exception {
        doNothing().when(cartService).removeFromCart(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/removeFromCart/{pId}", 1);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CartController#viewCart()}
     */
    @Test
    void testViewCart() throws Exception {
        when(cartService.getAllCartItems()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/viewCart");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CartController#viewCart()}
     */
    @Test
    void testViewCart2() throws Exception {
        when(cartService.getAllCartItems()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/viewCart");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

