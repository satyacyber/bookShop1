package com.cts.bookShop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#saveUser(User)}
     */
    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");
        when(userDao.save(Mockito.<User>any())).thenReturn(user);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserId(1);
        user2.setUserName("janedoe");
        assertSame(user, userService.saveUser(user2));
        verify(userDao).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertEquals("secret", user2.getPassword());
    }

    /**
     * Method under test: {@link UserService#fetchUserByEmailId(String)}
     */
    @Test
    void testFetchUserByEmailId() {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");
        when(userDao.findByEmailId(Mockito.<String>any())).thenReturn(user);
        assertSame(user, userService.fetchUserByEmailId("42"));
        verify(userDao).findByEmailId(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#fetchUserByEmailIdAndPassword(String, String)}
     */
    @Test
    void testFetchUserByEmailIdAndPassword() {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserId(1);
        user.setUserName("janedoe");
        when(userDao.findByEmailIdAndPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertSame(user, userService.fetchUserByEmailIdAndPassword("42", "iloveyou"));
        verify(userDao).findByEmailIdAndPassword(Mockito.<String>any(), Mockito.<String>any());
    }
}

