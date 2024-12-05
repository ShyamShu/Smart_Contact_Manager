package com.SCM.Smart_Contact_Manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.forms.UserForm;
import com.SCM.Smart_Contact_Manager.helper.Message;
import com.SCM.Smart_Contact_Manager.helper.MessageType;
import com.SCM.Smart_Contact_Manager.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class LoginOrRegisterController {

    @Autowired
    private UserServices userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

@PostMapping("/do-register")
public String regiisterHandler(@Valid @ModelAttribute UserForm userform ,BindingResult rBindingResult, HttpSession session) throws Exception  {
      
    logger.info("UserForm data: {}", userform.toString());
    logger.info(" string is reached here");

    // validate our form data 
    if(rBindingResult.hasErrors())
    {
        Message msg =  Message.builder().content("Registration is un Successfull ").type(MessageType.red).build();
        session.setAttribute("message", msg);
        return "register";
    }

    user users = user.builder()
    .name(userform.getName())
    .email(userform.getEmail())
    .about(userform.getAbout())
    .number(userform.getNumber())
    .password(userform.getPassword())
    .profilePic("https://images.unsplash.com/photo-1648740366598-7fb7c5e73fa5?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
    .build();
    userService.SaveUser(users);

    Message msg =  Message.builder().content("Registration Successfull ").type(MessageType.green).build();
    session.setAttribute("message", msg);
    
    return "redirect:/register";
}







}
