package com.SCM.Smart_Contact_Manager.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.Smart_Contact_Manager.services.UserServices;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/User")
public class UserControlller {

    Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserServices userServices;



// Dashboard url
@GetMapping("/Dashboard")
public String viewDashboard()
{
    return "userTemplate/dashboard";
}

// user profile page 
@GetMapping("/Profile")
public String viewProfile(Model model , Authentication authentication)
{
    return "userTemplate/profile";
}





// view contacts page
@GetMapping("/viewContacts")
public String viewContacts()
{
    return "";
}

}
