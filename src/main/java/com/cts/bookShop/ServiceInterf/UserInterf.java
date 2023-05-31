package com.cts.bookShop.ServiceInterf;

import com.cts.bookShop.entity.User;

public interface UserInterf {
    User saveUser(User user);
    User fetchUserByEmailId(String emailId);
    User fetchUserByEmailIdAndPassword(String emailId,String password);

}
