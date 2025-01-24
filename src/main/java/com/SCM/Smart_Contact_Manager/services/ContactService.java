package com.SCM.Smart_Contact_Manager.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manager.entities.Contact;
import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.repositories.ContactRepo;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepo contactRepo;

    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    public Contact saveContact(Contact contact) throws Exception
    {
        String uuid = UUID.randomUUID().toString();
        contact.setId(uuid);

        try{
        contactRepo.save(contact);
        }
        catch(Exception e){
            logger.info(e.toString());
            throw new Exception("problem is occur in savaing the contact");
        }
         return contact;
    }
 

    public Contact searchByName(String name)
    {
         Contact contact = contactRepo.findByname(name);

         if(contact == null)
         {
            return null;
         }
         return contact; 
    }

    public Optional<Contact> getUserByID(String id) {

        return contactRepo.findById(id);
    }

    public List<Contact> findAll(String email)
    {
        return contactRepo.findAll();
    }


    public Contact getContactByEmail(String email)
    {
       Contact contact =  contactRepo.findByemail(email);

       if(contact == null)
       {
        return null;
       }
       else{
        return contact;
       }
    }

    public List<Contact> findByUserID(String userID)
    {
        return contactRepo.findByuserId(userID);
    }

    public boolean isEmailExists(String email)
    {
       return  contactRepo.existsByEmail(email);
    }

    public List<Contact> findByUser(user user)
    {
        return contactRepo.findByusers(user);
    }

     
}
