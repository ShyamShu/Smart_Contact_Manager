package com.SCM.Smart_Contact_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/Contact")
public class contactController {

    @GetMapping("/addContact")
    public String addContactView()
    {
        return "userTemplate/addContact";
    }
    
    
}
