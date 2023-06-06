package com.cts.bookShop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.bookShop.dao.CartDao;
import com.cts.bookShop.dao.CartItemDao;
import com.cts.bookShop.dao.ProductDao;
import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.Cart;
import com.cts.bookShop.entity.CartItem;
import com.cts.bookShop.entity.Product;
import com.cts.bookShop.entity.User;
import com.cts.bookShop.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartService.class})
@ExtendWith(SpringExtension.class)
class CartServiceTest {
    @MockBean
    private CartDao cartDao;

    @MockBean
    private CartItemDao cartItemDao;

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductDao productDao;

    @MockBean
    private UserDao userDao;

    /**
     * Method under test: {@link CartService#addToCart(Integer)}
     */
    @Test
    void testAddToCart() {
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

        Cart cart2 = new Cart();
        cart2.setCartId(1);
        cart2.setCartItems(new ArrayList<>());
        cart2.setUser(user2);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);

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
        when(cartItemDao.save(Mockito.<CartItem>any())).thenReturn(cartItem);

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
        Optional<Product> ofResult = Optional.of(product2);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);
        assertSame(cart, cartService.addToCart(1));
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao, atLeast(1)).save(Mockito.<Cart>any());
        verify(cartItemDao).save(Mockito.<CartItem>any());
        verify(productDao).findById(Mockito.<Integer>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#addToCart(Integer)}
     */
    @Test
    void testAddToCart2() {
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

        Cart cart2 = new Cart();
        cart2.setCartId(1);
        cart2.setCartItems(new ArrayList<>());
        cart2.setUser(user2);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);
        when(cartItemDao.save(Mockito.<CartItem>any())).thenThrow(new ResourceNotFoundException("Msg"));

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
        Optional<Product> ofResult = Optional.of(product);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);
        assertThrows(ResourceNotFoundException.class, () -> cartService.addToCart(1));
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(cartItemDao).save(Mockito.<CartItem>any());
        verify(productDao).findById(Mockito.<Integer>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#addToCart(Integer)}
     */
    @Test
    void testAddToCart3() {
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
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);

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

        CartItem cartItem2 = new CartItem();
        cartItem2.setCust_quantity(1);
        cartItem2.setId(1);
        cartItem2.setProduct(product2);
        when(cartItemDao.save(Mockito.<CartItem>any())).thenReturn(cartItem2);

        Product product3 = new Product();
        product3.setAuthor("JaneDoe");
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setISBN("ISBN");
        product3.setImgUrl("https://example.org/example");
        product3.setName("Name");
        product3.setPId(1);
        product3.setPrice(10.0d);
        product3.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product3);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);
        assertSame(cart, cartService.addToCart(1));
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(productDao).findById(Mockito.<Integer>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#addToCart(Integer)}
     */
    @Test
    void testAddToCart4() {
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

        Product product2 = new Product();
        product2.setAuthor("Author");
        product2.setCategory("com.cts.bookShop.entity.Product");
        product2.setDescription("Description");
        product2.setISBN("com.cts.bookShop.entity.Product");
        product2.setImgUrl("Img Url");
        product2.setName("com.cts.bookShop.entity.Product");
        product2.setPId(2);
        product2.setPrice(0.5d);
        product2.setQuantity(0);

        CartItem cartItem2 = new CartItem();
        cartItem2.setCust_quantity(0);
        cartItem2.setId(2);
        cartItem2.setProduct(product2);

        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem2);
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
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);

        Product product3 = new Product();
        product3.setAuthor("JaneDoe");
        product3.setCategory("Category");
        product3.setDescription("The characteristics of someone or something");
        product3.setISBN("ISBN");
        product3.setImgUrl("https://example.org/example");
        product3.setName("Name");
        product3.setPId(1);
        product3.setPrice(10.0d);
        product3.setQuantity(1);

        CartItem cartItem3 = new CartItem();
        cartItem3.setCust_quantity(1);
        cartItem3.setId(1);
        cartItem3.setProduct(product3);
        when(cartItemDao.save(Mockito.<CartItem>any())).thenReturn(cartItem3);

        Product product4 = new Product();
        product4.setAuthor("JaneDoe");
        product4.setCategory("Category");
        product4.setDescription("The characteristics of someone or something");
        product4.setISBN("ISBN");
        product4.setImgUrl("https://example.org/example");
        product4.setName("Name");
        product4.setPId(1);
        product4.setPrice(10.0d);
        product4.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product4);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);
        assertSame(cart, cartService.addToCart(1));
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(productDao).findById(Mockito.<Integer>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(Integer)}
     */
    @Test
    void testRemoveFromCart() {
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

        Cart cart2 = new Cart();
        cart2.setCartId(1);
        cart2.setCartItems(new ArrayList<>());
        cart2.setUser(user2);
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        cartService.removeFromCart(1);
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(userDao).findByUserName(Mockito.<String>any());
        assertTrue(cartService.getAllCartItems().isEmpty());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(Integer)}
     */
    @Test
    void testRemoveFromCart2() {
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
        when(cartDao.save(Mockito.<Cart>any())).thenThrow(new ResourceNotFoundException("Msg"));
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user2);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> cartService.removeFromCart(1));
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(Integer)}
     */
    @Test
    void testRemoveFromCart3() {
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
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        cartService.removeFromCart(1);
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(userDao).findByUserName(Mockito.<String>any());
        assertTrue(cartService.getAllCartItems().isEmpty());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(Integer)}
     */
    @Test
    void testRemoveFromCart4() {
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

        Product product2 = new Product();
        product2.setAuthor("Author");
        product2.setCategory("com.cts.bookShop.entity.Product");
        product2.setDescription("Description");
        product2.setISBN("com.cts.bookShop.entity.Product");
        product2.setImgUrl("Img Url");
        product2.setName("com.cts.bookShop.entity.Product");
        product2.setPId(2);
        product2.setPrice(0.5d);
        product2.setQuantity(0);

        CartItem cartItem2 = new CartItem();
        cartItem2.setCust_quantity(0);
        cartItem2.setId(2);
        cartItem2.setProduct(product2);

        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem2);
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
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user3 = new User();
        user3.setEmailId("42");
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserId(1);
        user3.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user3);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        cartService.removeFromCart(1);
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(cartDao).save(Mockito.<Cart>any());
        verify(userDao).findByUserName(Mockito.<String>any());
        assertEquals(1, cartService.getAllCartItems().size());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveFromCart5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.cts.bookShop.service.CartService.removeFromCart(CartService.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

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

        Cart cart2 = new Cart();
        cart2.setCartId(1);
        cart2.setCartItems(new ArrayList<>());
        cart2.setUser(user2);
        when(cartDao.save(Mockito.<Cart>any())).thenReturn(cart2);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(Optional.empty());
        cartService.removeFromCart(1);
    }

    /**
     * Method under test: {@link CartService#getAllCartItems()}
     */
    @Test
    void testGetAllCartItems() {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");

        Cart cart = new Cart();
        cart.setCartId(1);
        ArrayList<CartItem> cartItems = new ArrayList<>();
        cart.setCartItems(cartItems);
        cart.setUser(user);
        when(cartDao.findByUser(Mockito.<User>any())).thenReturn(cart);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user2);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        List<CartItem> actualAllCartItems = cartService.getAllCartItems();
        assertSame(cartItems, actualAllCartItems);
        assertTrue(actualAllCartItems.isEmpty());
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#getAllCartItems()}
     */
    @Test
    void testGetAllCartItems2() {
        when(cartDao.findByUser(Mockito.<User>any())).thenThrow(new ResourceNotFoundException("Cart not found"));

        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> cartService.getAllCartItems());
        verify(cartDao).findByUser(Mockito.<User>any());
        verify(userDao).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CartService#getAllCartItems()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCartItems3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.cts.bookShop.dao.UserDao.findByUserName(String)" is null
        //       at com.cts.bookShop.service.CartService.getAllCartItems(CartService.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(userDao.findByUserName(Mockito.<String>any())).thenReturn(null);
        cartService.getAllCartItems();
    }
}

