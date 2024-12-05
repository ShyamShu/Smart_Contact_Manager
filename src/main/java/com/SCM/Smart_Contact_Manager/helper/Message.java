package com.SCM.Smart_Contact_Manager.helper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String content;

    private MessageType type ;

}
