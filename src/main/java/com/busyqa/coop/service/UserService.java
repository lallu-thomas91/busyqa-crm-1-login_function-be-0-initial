package com.busyqa.coop.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.busyqa.coop.jpa.User;
import com.busyqa.coop.repository.IUserRepository;
//import com.busyqa.coop.security.AuthenticationFilter;

@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
//	@Autowired
//    AuthenticationFilter authenticationFilter;


    @Autowired
	IUserRepository userRepository;

    
    @Transactional(propagation=Propagation.REQUIRED)
    public void signupUser(User user){
    	
    	this.validateNewUser(user);
    	    	
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	
    	this.userRepository.save(user);
    }
    
    /*
    @Transactional(readOnly=true)
    public boolean authenticateUser(String inputUsername, String inputPassword){
    	
    	boolean authenticated = false;
    	
    	logger.debug("Attempting authentication for " + inputUsername);
    	
    	if (this.authenticationFilter.attemptAuthentication(inputUsername, inputPassword) != null) {
    		authenticated = true;
    	}
    	else {
    		authenticated = false;
    	}
    	
    	return authenticated;
	}
	*/
    
    
    @Transactional(readOnly=true)
    public User authenticateUser(String inputUsername, String inputPassword) {
    	
    	String encodedPassword = passwordEncoder.encode(inputPassword);
    	
    	User user = this.userRepository.findByUsernameAndPassword(inputUsername, encodedPassword);
       
    	logger.debug("Available user with username " + inputUsername + ": " + user);
           
       if(user != null ){
    	   
    	   return user;
       }
       else {
    	   throw new BadCredentialsException("Invalid credentials");
       }
	}
    
    
    @Transactional(readOnly=true)
    public User findUser(String username){
       
    	return this.userRepository.findByUsername(username);    
	}


    private void validateNewUser(User user) {
        /*
         * Validate user Data.
         */
        if (user.getFirstName().length()<6 ||
            user.getLastName().length()<6 ||
            user.getUsername().length()<6 ||
            user.getPassword().length()<6 ||
            user.getEmail().length()<6 ||
            user.getPhone().length()<10 ||
            user.getRole().isEmpty() ){
            	throw new RuntimeException("Invalid User Data " + user);
        	}
    }
}
