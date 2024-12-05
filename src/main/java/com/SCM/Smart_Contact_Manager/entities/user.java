package com.SCM.Smart_Contact_Manager.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class user implements UserDetails  {


    @Id
    private String userId;
    @Column(nullable =  false)
    private String name;
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true)
    private String email;
    private String about;
    private String profilePic;
    private String number;


    private boolean enabled;
    private boolean phoneVerified;
    private boolean emailVerified;


    private String providers;
    private String providerID;

    // add more fields if need by using one to many mapping

 
    
    @OneToMany (mappedBy =  "users" , cascade = CascadeType.ALL , fetch =  FetchType.LAZY , orphanRemoval  = true)
    private List<Contact> contactList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
               return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
       
        return this.email;
    }
    

    



}
