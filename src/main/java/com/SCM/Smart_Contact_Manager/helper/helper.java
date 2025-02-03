package com.SCM.Smart_Contact_Manager.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class helper {

    public static String getEmailOfLoggedInUser(Authentication  authentication)
    {
        // checking the user is signed with social logins  or get registered with tradition form
        if(authentication instanceof OAuth2AuthenticationToken)
        {
             var authoriztionProvider = (OAuth2AuthenticationToken)authentication;
                String provider = authoriztionProvider.getAuthorizedClientRegistrationId();
                DefaultOAuth2User user  = (DefaultOAuth2User)authentication.getPrincipal();
                if(provider.equalsIgnoreCase("google"))
                {
                  System.out.println("login with google ");
                  return user.getAttribute("email");
                }
                else 
                {
                   System.out.println("login with github ");
                   return ( (user.getAttribute("email") != null ? user.getAttribute("email").toString() : user.getAttribute("login").toString() + "@gmail.com"));
                }
        }
        else{
           return authentication.getName();
        }
    }
    
}
