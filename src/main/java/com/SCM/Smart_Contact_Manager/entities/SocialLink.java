package com.SCM.Smart_Contact_Manager.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String tittle;

    @ManyToOne
    private  Contact contacts;

}
