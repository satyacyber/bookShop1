package com.cts.bookShop.service;

import com.cts.bookShop.ServiceInterf.UserInterf;
import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements UserInterf {

        @Autowired
        private UserDao userdao;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public User saveUser(User user){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userdao.save(user);
        }
        public User fetchUserByEmailId(String emailId){
                return userdao.findByEmailId(emailId);
        }

        public User fetchUserByEmailIdAndPassword(String emailId,String password){
                return userdao.findByEmailIdAndPassword(emailId,password);
        }
}
