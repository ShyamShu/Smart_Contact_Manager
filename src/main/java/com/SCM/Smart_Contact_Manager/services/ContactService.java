package com.SCM.Smart_Contact_Manager.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manager.entities.Contact;
import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.repositories.ContactRepo;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepo contactRepo;

    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    // Save the contact 
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
 
    // serach by name 
    public Contact searchByName(String name)
    {
         Contact contact = contactRepo.findByname(name);

         if(contact == null)
         {
            return null;
         }
         return contact; 
    }

     // get user by id
    public Optional<Contact> getUserByID(String id) {

        return contactRepo.findById(id);
    }

    // list of all contact through email
    public List<Contact> findAll(String email)
    {
        return contactRepo.findAll();
    }

    // provide contact related to email 
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

    // return all the list of conatct realted to user id 
    public List<Contact> findByUserID(String userID)
    {
        return contactRepo.findByuserId(userID);
    }

    //check this email is exist or not 
    public boolean isEmailExists(String email)
    {
       return  contactRepo.existsByEmail(email);
    }


    // find the contact list reated to user 
    public List<Contact> findByUser(user user )
    {
        return contactRepo.findByusers(user);
    }


    // get the pages of conatct  realated to user 
    public Page<Contact> findByUser(user user , int page , int size , String sortBy, String direction )
    {
        org.springframework.data.domain.Sort sort = direction.equals("desc") ? org.springframework.data.domain.Sort.by(sortBy).descending() : org.springframework.data.domain.Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size , sort);
        return contactRepo.findByusers(user , pageable);
    }



    // search the contact related to the user  with the help of name
    public Page<Contact> searchName( String nameKeyword , int page , int size ,String sortBy , String order , user user)
    {
        org.springframework.data.domain.Sort sort = order.equals("desc") ? org.springframework.data.domain.Sort.by(sortBy).descending() : org.springframework.data.domain.Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size , sort);
        return contactRepo.findByUsersAndNameContaining(user , nameKeyword ,  pageable);
    }

    // search the contact related to the user with the help of email
    public Page<Contact> searchEmail( String emailKeyword , int page , int size ,String sortBy , String order , user user)
    {
        org.springframework.data.domain.Sort sort = order.equals("desc") ? org.springframework.data.domain.Sort.by(sortBy).descending() : org.springframework.data.domain.Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size , sort);
        return contactRepo.findByUsersAndEmailContaining(user , emailKeyword, pageable );
    }

    //search the contact related to the user with the help of phone no
    public Page<Contact> searchPhoneNo( String PhoneNumberKeyword , int page , int size ,String sortBy , String order , user user)
    {
        org.springframework.data.domain.Sort sort = order.equals("desc") ? org.springframework.data.domain.Sort.by(sortBy).descending() : org.springframework.data.domain.Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size , sort);
        return contactRepo.findByUsersAndPhoneNumberContaining(user , PhoneNumberKeyword , pageable);
    }
     
}
