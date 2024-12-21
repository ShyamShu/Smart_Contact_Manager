package com.SCM.Smart_Contact_Manager.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.Smart_Contact_Manager.entities.Contact;
import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.forms.ContactForm;
import com.SCM.Smart_Contact_Manager.helper.Message;
import com.SCM.Smart_Contact_Manager.helper.MessageType;
import com.SCM.Smart_Contact_Manager.helper.helper;
import com.SCM.Smart_Contact_Manager.services.ContactService;
import com.SCM.Smart_Contact_Manager.services.UserServices;
import com.SCM.Smart_Contact_Manager.services.imageService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("User/Contact")
public class contactController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ContactService contactService;

    @Autowired
    private imageService imageService;
    @Autowired
    private UserServices userServices;


    //this is used to send html template to online 
    @GetMapping("/addContact")
    public String addContactView(Model model)
    {
        ContactForm contactForm = new ContactForm();
        
        model.addAttribute("contactForm", contactForm);

        //itreturn the add contact htmlfile 
        return "userTemplate/addContact";
    }

    //it is used to save the contact and return the same page of add contact 
    @PostMapping("/addContact")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,org.springframework.security.core.Authentication authentication,HttpSession session) throws Exception {
         //checking the contact  has error or not
        if(result.hasErrors())
         {
            Message  message = Message.builder().content("Pls Correct the Errors before Submitting ").type(MessageType.red).build();
            session.setAttribute("message", message);
            logger.info(result.toString());
            return "userTemplate/addContact" ;
         }//if not then save the user 
         else{   
            
            // finding the user who save this contact into its list

            String emailOfUser = helper.getEmailOfLoggedInUser(authentication);
            user user= userServices.getUserByEmail(emailOfUser);

            
            logger.info("file Information : {} " , contactForm.getPicture().getOriginalFilename());
            String FileName = UUID.randomUUID().toString();
            String imageURL = imageService.upload(contactForm.getPicture() , FileName);
            logger.info("my profile url is : {}" , imageURL);
            Contact contact = new Contact();
            contact.setName(contactForm.getName());
            contact.setEmail(contactForm.getEmail());
            contact.setAddress(contactForm.getAddress());
            contact.setDiscription(contactForm.getDiscription());
            contact.setFavorite(contactForm.isFavorite());
            contact.setLinkedin(contactForm.getLinkedin());
            contact.setWebsite(contactForm.getWebsite());
            contact.setPhoneNumber(contactForm.getPhoneNumber());
            contact.setPictures(imageURL);
            contact.setCloudinaryPublicID(FileName);
            contact.setUsers(user);
            
             
            logger.info(contactService.saveContact(contact).toString());
            Message msg =  Message.builder().content("Your Contact is Added successfully  ").type(MessageType.green).build();
            session.setAttribute("message", msg);
            
         }

        //redirect to the that api that show the addcontact html file 
        return "redirect:/User/Contact/addContact";
    }

    //Edit the contact API
    @GetMapping("/editContact")
    public String editContact(Model model)
    {
        ContactForm contactForm = new ContactForm();
        
        model.addAttribute("contactForm", contactForm);
        return "userTemplate/editContact";
    }


    






    
    
    
}
