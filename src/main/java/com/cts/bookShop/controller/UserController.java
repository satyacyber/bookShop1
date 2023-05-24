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

    private static final String user_role="USER";
    @PostMapping("/signup")
        public User registerUser (@RequestBody User user) throws Exception{
        user.setRole(user_role);
        //System.out.println(user.getRole());
        return  userservice.saveUser(user);

    }
//    @GetMapping("/login")
//    public User loginUser(@RequestBody User user) throws Exception{
//        String tempMail=user.getEmailId();
//        String tempPassword=user.getPassword();
//        //String tempPassword=passwordEncoder.encode(user.getPassword());
//        System.out.println(tempPassword);
//        User userObj=null;
//        if(tempPassword!=null && tempMail!=null){
//            userObj=userservice.fetchUserByEmailIdAndPassword(tempMail,tempPassword);
//        }
//        if(userObj==null){
//            throw new ResourceNotFoundException("Bad Credentials");
//        }
//        CURRENT_USER=userObj.getUserName();
//        return userObj;
//
//    }
@GetMapping("/login")
public User loginUser(@RequestBody User user) throws Exception {
    String tempMail = user.getEmailId();
    String tempPassword = user.getPassword();
    User userObj = null;

    if (tempPassword != null && tempMail != null) {
        // Retrieve the user based on the provided email
        User existingUser = userservice.fetchUserByEmailId(tempMail);

        if (existingUser != null) {
            // Use the BCryptPasswordEncoder to compare the entered password with the stored encoded password
            boolean passwordMatches = passwordEncoder.matches(tempPassword, existingUser.getPassword());

            if (passwordMatches) {
                userObj = existingUser;
            }
        }
    }

    if (userObj == null) {
        throw new ResourceNotFoundException("Bad Credentials");
    }

    CURRENT_USER = userObj.getUserName();
    return userObj;
}

}
