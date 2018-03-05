package com.icrn.yamr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed
    @NotEmpty(message = "This field is required")
    private String email;

    @NotEmpty(message = "This field is required")
    private String firstName;

    @NotEmpty(message = "This field is required")
    private String lastName;

    @NotEmpty(message = "This field is required")
    private String password;

    public User(User user) {
        this.id         = user.getId();
        this.email      = user.getEmail();
        this.password   = user.getPassword();
        this.firstName  = user.getFirstName();
        this.lastName   = user.getLastName();


    }

//    public User(String id, String email, String password, String firstName,
//                String lastName) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
}
