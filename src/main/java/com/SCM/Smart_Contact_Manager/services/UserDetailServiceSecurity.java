package com.SCM.Smart_Contact_Manager.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manager.helper.ResourceNotFoundException;
import com.SCM.Smart_Contact_Manager.repositories.UserRepo;



@Service  // it is an service 
public class UserDetailServiceSecurity implements UserDetailsService  // it is am interface that implements by thus class
{

    @Autowired
    private UserRepo userRepo;

     // it is used to log info on my screens for confirmation of any method or showing data that confirs my method is running this log 
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override // that is the unimplemented function of this inetrface 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(username);
        

        // return the user info from data if it is there other wise throwing exception on the screenthat email is not in database
       return userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException(" This Email Is not found in Database" + username + " here it is an email"));
        
    }

}
