package com.cts.bookShop.controller;

import static org.mockito.Mockito.when;

import com.cts.bookShop.entity.User;
import com.cts.bookShop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    /**
     * Method under test: {@link UserController#loginUser(User)}
     */
    @Test
    void testLoginUser() throws Exception {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");
        when(userService.fetchUserByEmailId(Mockito.<String>any())).thenReturn(user);
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"emailId\":\"42\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"role\":\"Role\"}"));
    }

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#registerUser(User)}
     */
    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");
        when(userService.saveUser(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"emailId\":\"42\",\"userName\":\"janedoe\",\"password\":\"iloveyou\",\"role\":\"Role\"}"));
    }
}

