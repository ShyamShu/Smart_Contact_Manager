package com.SCM.Smart_Contact_Manager.entities;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity

public class Contact {
    
   @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String pictures;
    private String Linkedin;
    private String Website;

    @Column (length = 1000)
    private String discription ;
    private boolean favorite = false;
    private String CloudinaryPublicID;

    @ManyToOne
    @JsonIgnore
    private user users;

    @OneToMany (mappedBy =  "contacts" , cascade = CascadeType.ALL , fetch =  FetchType.LAZY , orphanRemoval  = true)
    private List<SocialLink> links = new ArrayList<>();


}
