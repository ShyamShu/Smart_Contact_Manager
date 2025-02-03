package com.SCM.Smart_Contact_Manager.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SCM.Smart_Contact_Manager.entities.Contact;
import com.SCM.Smart_Contact_Manager.entities.user;

@Repository
@EnableJpaRepositories
public interface ContactRepo extends JpaRepository<Contact,String> {

    public Contact findByname(String name);

    public Contact findByemail(String email);  

    List<Contact> findByusers(user users); 

    @Query("SELECT c FROM Contact c WHERE c.users.id = :userId")
    List<Contact> findByuserId(@Param("userId")String userId);

    public boolean existsByEmail(String Email);

    Page<Contact> findByusers(user user , Pageable pageable);

    Page<Contact> findByUsersAndNameContaining (user users , String nameKeyword , Pageable pageable);
    Page<Contact> findByUsersAndEmailContaining (user users , String emailKeyword ,  Pageable pageable);
    Page<Contact> findByUsersAndPhoneNumberContaining ( user users , String PhoneNumberKeyword ,  Pageable pageable);


}
