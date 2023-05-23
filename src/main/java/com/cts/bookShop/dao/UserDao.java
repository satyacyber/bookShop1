package com.cts.bookShop.dao;

import com.cts.bookShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
        User findByEmailId(String email);
        User findByEmailIdAndPassword(String emailId, String Password);
        User findByUserName(String currentUser);
}
