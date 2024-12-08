package com.SCM.Smart_Contact_Manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.Smart_Contact_Manager.forms.ContactForm;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("User/Contact")
public class contactController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    //this is used to send html template to online 
    @GetMapping("/addContact")
    public String addContactView(Model model)
    {
        ContactForm contactForm = new ContactForm();
        contactForm.setName("shyam");
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);

        //itreturn the add contact htmlfile 
        return "userTemplate/addContact";
    }

    //it is used to save the contact and return the same page of add contact 
    @PostMapping("/addContact")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result) {
         //checking the contact  has error or not
        if(result.hasErrors())
         {
            logger.info(result.toString());
         }//if not then save the user 
         else{
            logger.info(contactForm.toString());
         }

        //redirect to the that api that show the addcontact html file 
        return "redirect:/User/Contact/addContact";
    }

    //Edit the contact API
    @GetMapping("/editContact")
    public String editContact()
    {
        return "";
    }

    






    
    
    
}
