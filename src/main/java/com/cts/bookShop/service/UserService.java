package com.cts.bookShop.service;

import com.cts.bookShop.dao.UserDao;
import com.cts.bookShop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        @Autowired
        private UserDao userdao;

        public User saveUser(User user){
            return userdao.save(user);
        }
        public User fetchUserByEmailId(String emailId){
                return userdao.findByEmailId(emailId);
        }

        public User fetchUserByEmailIdAndPassword(String emailId,String password){
                return userdao.findByEmailIdAndPassword(emailId,password);
        }
}
