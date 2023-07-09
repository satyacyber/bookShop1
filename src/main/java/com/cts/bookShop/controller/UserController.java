package com.cts.bookShop.controller;


import com.cts.bookShop.entity.User;
import com.cts.bookShop.exception.ResourceNotFoundException;
import com.cts.bookShop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    public static String  CURRENT_USER="";
    @Autowired
    public UserService userservice;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String user_role="USER";
    @PostMapping("/signup")
    @CrossOrigin(origins="http://localhost:3000")
        public User registerUser (@RequestBody User user) throws Exception{
        logger.info("Registering user: {}", user);
        if(user.getUserName()==null|| user.getEmailId()==null|| user.getPassword()==null) {
            throw new ResourceNotFoundException("Feild Missing");
        }
        user.setRole(user_role);

        return  userservice.saveUser(user);

    }

@CrossOrigin(origins="http://localhost:3000")
@PostMapping("/login")
public ResponseEntity<User> loginUser(@RequestBody User user) throws Exception {
    logger.info("User login: {}", user.getEmailId());
    String tempMail = user.getEmailId();
    String tempPassword = user.getPassword();
    User userObj = null;
        //check for validation
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
        logger.error("Bad Credentials for user: {}", user.getEmailId());
        throw new ResourceNotFoundException("Bad Credentials");
    }

    CURRENT_USER = userObj.getUserName();
    return ResponseEntity.ok(userObj);
}

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        logger.info("User logout");
        CURRENT_USER = "";
        return new ResponseEntity<>("logged-out", HttpStatus.OK);
    }


}
