package com.SCM.Smart_Contact_Manager.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/User")
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
        return "redirect:/User/addContact";
    }


    //Edit the contact API
    @GetMapping("/editContact")
    public String editContact(Model model)
    {
        ContactForm contactForm = new ContactForm();
        
        model.addAttribute("contactForm", contactForm);
        return "userTemplate/editContact";
    }



    // provides the all contact list in the page form to display to user
    @GetMapping("/AllContact")
    public String allContact(
        @RequestParam(value = "page"  , defaultValue =  "0") int page,
        @RequestParam(value = "size" , defaultValue = "6" ) int size,
        @RequestParam(value = "sortBy" , defaultValue = "name") String sortBy,
        @RequestParam(value = "direction" , defaultValue = "asc") String direction,
        org.springframework.security.core.Authentication authentication , Model model )
    {
        String email = helper.getEmailOfLoggedInUser(authentication);
        user user = userServices.getUserByEmail(email);
        Page<Contact> pageContact = contactService.findByUser(user, page, size , sortBy , direction);

         model.addAttribute("pageContact", pageContact);
        return "userTemplate/allContact";
    }


    @GetMapping("/search")
    public String searchHandler(
        @RequestParam(value = "page"  , defaultValue =  "0") int page,
        @RequestParam(value = "size" , defaultValue = "6" ) int size,
        @RequestParam(value = "sortBy" , defaultValue = "name") String sortBy,
        @RequestParam(value = "direction" , defaultValue = "asc") String direction,
        @RequestParam("field") String field,
        @RequestParam("keyword") String word,
        Model model,
        org.springframework.security.core.Authentication authentication
    )
    {
        String email = helper.getEmailOfLoggedInUser(authentication);
        user user = userServices.getUserByEmail(email);
        logger.info("log comming form contact controller with search method");
        logger.info("field = " + field);
        logger.info("keyword = " + word);

        Page<Contact> pageContact = null;
        if(field.equalsIgnoreCase("Name"))
        {
            logger.info("name search is called");
           pageContact = contactService.searchName(word, page, size, sortBy, direction ,  user);
        }
        else if(field.equalsIgnoreCase("Email"))
        {
            logger.info("email search is called");
           pageContact = contactService.searchEmail(word, page, size, sortBy, direction ,  user);
        }
        else{
            logger.info("phone search is called");
            pageContact = contactService.searchPhoneNo(word, page, size, sortBy, direction ,  user);
        }

        logger.info("pageContact + {}" , pageContact.getContent());
        model.addAttribute("searchPageContact", pageContact);
        return "userTemplate/search";
    }

    






    
    
    
}
