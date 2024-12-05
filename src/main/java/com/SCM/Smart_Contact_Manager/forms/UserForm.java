package com.SCM.Smart_Contact_Manager.forms;

        
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    
    @NotBlank(message = " name is required ")
    @Size(min= 3 , message =  " min 2 characters needed")
    private String name;

    @NotBlank(message = "password is required")
    @Size(min = 6 , max = 15 , message =  " minimum length of password is 8 ")
    private String password;
    @Size(min=8 , max = 12 , message = " pls fill correct mobile number")
    private String number;
    @NotBlank(message = " pls fill minimum one line about your contact")
    private String about;
    
    @Email(message = " not a valid email type")
    private String email;
}
