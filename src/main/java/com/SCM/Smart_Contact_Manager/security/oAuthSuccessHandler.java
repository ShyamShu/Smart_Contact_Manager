package com.SCM.Smart_Contact_Manager.security;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.SCM.Smart_Contact_Manager.entities.Providers;
import com.SCM.Smart_Contact_Manager.entities.user;
import com.SCM.Smart_Contact_Manager.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class oAuthSuccessHandler implements AuthenticationSuccessHandler {

  Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserRepo userRepo;

  @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

               //Checking which provider is used to login in my system 
                var authoriztionProvider = (OAuth2AuthenticationToken)authentication;
                String provider = authoriztionProvider.getAuthorizedClientRegistrationId();
            
                logger.info("provider : {}" , provider);

                // Creating  a default OAuth user that conatin all the neccesary credentials like name an all the stuff that goes into my nackend database
                DefaultOAuth2User user  = (DefaultOAuth2User)authentication.getPrincipal();

                // Printing the data into logs for testing that confirs user is arived here 
               
                logger.info(" my incomming data from ");
                user.getAttributes().forEach((key,value)->{
                logger.info("{}:{}" , key,value);
                });
                logger.info(user.getAuthorities().toString());

                //Creating a user with the help of data which comes from the authentication variable
                user user2 = new user();
                user2.setUserId(UUID.randomUUID().toString());
                user2.setEnabled(true);
                user2.setPassword("123456");
                String email = (user.getAttribute("email") != null ? user.getAttribute("email").toString() : user.getAttribute("login").toString() + "@gmail.com");
                logger.info(" email of incooming user in oauth success handler : {}" , email);
                // check the authorizatiom method is google 
                if(provider.equalsIgnoreCase("google"))
                {
                      // add the constraint which are comes from google authentication 
                      user2.setName(user.getAttribute("name").toString());
                      user2.setEmail(user.getAttribute("email").toString()); 
                      user2.setProfilePic(user.getAttribute("picture").toString());
                      user2.setProviderID(Providers.GOOGLE.toString());
                      user2.setEmailVerified(true);    
                      user2.setAbout("that is my google account which are used for authentication "); 
                }



                // check the authorization method is github
                else if(provider.equalsIgnoreCase("github"))
                {
                  // add the constraint which are comes from github authentication 
                    user2.setName(user.getAttribute("login").toString());
                    user2.setEmail(email);
                    user2.setProfilePic(user.getAttribute("avatar_url").toString());
                    user2.setProviderID(Providers.GITHUB.toString());
                    user2.setEmailVerified(false);   
                    user2.setAbout("that is my github account which are used for authentication ");
                }


                // Checking the user email is exist in database or not 
                user user3 = userRepo.findByEmail(email).orElse(null);
              
        
                if(user3 == null)
                {
                  // Saving the user in database that create above here 
                  userRepo.save(user2);
                  logger.info("  user is saved with this email" + user2.getEmail());
                }
                else{
                  logger.info(" there is a existing user with this email");
                }
                
                // forwarding the user or redirecting the user to next user page after successfull authentication  
                new DefaultRedirectStrategy().sendRedirect(request, response, "/User/Dashboard");
    }

}
