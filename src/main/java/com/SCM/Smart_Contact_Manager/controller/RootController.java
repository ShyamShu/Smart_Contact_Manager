package com.SCM.Smart_Contact_Manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.helper.helper;
import com.SCM.Smart_Contact_Manager.services.UserServices;

@ControllerAdvice
public class RootController {

        Logger logger =  LoggerFactory.getLogger(this.getClass());
         @Autowired
          UserServices userServices;


    // This function helps to get user details  after login and it used anywhere in whole class
    @ModelAttribute
    public void addLoggedInUser(Model model , Authentication authentication)
    {

        if(authentication == null)
        {
            logger.info("Authentication object: {}", authentication);

            return;
        }
        try {
            String email = helper.getEmailOfLoggedInUser(authentication);
            logger.info("Logged-in user's email: {}", email);

            user user = userServices.getUserByEmail(email);

            if (user == null) {
                logger.warn("No user found for email: {}", email);
            } else {
                logger.info("Logged-in user: {}", user.getName());
                model.addAttribute("LoggedInUser", user);
            }
        } catch (Exception e) {
            logger.error("Error fetching logged-in user details", e);
        }

       
           
        
        
    }

     
}