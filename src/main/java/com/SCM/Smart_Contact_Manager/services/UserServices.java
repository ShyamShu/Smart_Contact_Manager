package com.SCM.Smart_Contact_Manager.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.repositories.UserRepo;

@Service
public class UserServices {

    @Autowired
    UserRepo userRepo;

    // private Logger logger = LoggerFactory.getLogger(this.getClass());

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    // Save the User in Our DataBase
    public user SaveUser(user user) throws Exception {
        String Userid = UUID.randomUUID().toString();
        user.setUserId(Userid);
        String Password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(Password));
        user.setEnabled(true);


         try {
             userRepo.save(user);
             
        } catch (DataIntegrityViolationException e) {
            throw new Exception("Email already exists!");
        }

        return user;
        
    }

    // get user by their unique id
    public Optional<user> getUserByID(String id) {

        return userRepo.findById(id);
    }

    // Update User
    public user UpdateUser(user user) {

        user user2 = userRepo.findById(user.getUserId()).orElseThrow();

        user2.setName(user.getName());
        user2.setAbout(user.getAbout());
        user2.setPassword(user.getPassword());
        user2.setEmail(user.getEmail());
        user2.setNumber(user.getNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setEnabled(user.isEnabled());
        user2.setProviderID(user.getProviderID());
        user2.setPhoneVerified(user.isPhoneVerified());

        return userRepo.save(user2);
    }

    // delete user by using id
    public void deleteUser(String id) {
        user user2 = userRepo.findById(id).orElseThrow();

        userRepo.delete(user2);
    }

    // check that user exist in our database or not
    public boolean isExistUser(String id) {
        user user2 = userRepo.findById(id).orElse(null);

        return user2 == null ? false : true;
    }

    public user getUserByEmail(String email)
    {
        return userRepo.findByEmail(email).orElse(null);
    }

}
