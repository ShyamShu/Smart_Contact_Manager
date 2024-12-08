package com.SCM.Smart_Contact_Manager.forms;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String discription;
    private String Linkedin;
    private String Website;
    
    private boolean favorite;
    private MultipartFile picture;
    

    
}
