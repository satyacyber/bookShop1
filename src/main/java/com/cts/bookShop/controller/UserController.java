package com.cts.bookShop.controller;


import com.cts.bookShop.entity.User;
import com.cts.bookShop.exception.ResourceNotFoundException;
import com.cts.bookShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {

    public static String  CURRENT_USER="";
    @Autowired
    public UserService userservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
        public User registerUser (@RequestBody User user) throws Exception{
        return  userservice.saveUser(user);

    }
    @GetMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception{
        String tempMail=user.getEmailId();
        String tempPassword=user.getPassword();
        User userObj=null;
        if(tempPassword!=null && tempMail!=null){
            userObj=userservice.fetchUserByEmailIdAndPassword(tempMail,tempPassword);
        }
        if(userObj==null){
            throw new ResourceNotFoundException("Bad Credentials");
        }
        CURRENT_USER=userObj.getUserName();
        return userObj;

    }

}
