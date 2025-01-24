package com.SCM.Smart_Contact_Manager.forms;

import org.springframework.web.multipart.MultipartFile;

import com.SCM.Smart_Contact_Manager.validate.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name of Contact is Required ")
    private String name;

    @Email(message = "Not a Valid Email type")
    @NotBlank(message = "Email is Compulsory")
    private String email;

    @Size(min = 8,max = 10)
    private String phoneNumber;

    
    private String address;

    @NotBlank(message = " Pls fill minimum one line about  contact")
    private String discription;
    private String Linkedin;
    private String Website;
    
    private boolean favorite;

    @ValidFile
    private MultipartFile picture;
    

    
}
