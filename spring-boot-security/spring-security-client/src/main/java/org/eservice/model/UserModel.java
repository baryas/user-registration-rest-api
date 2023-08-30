package org.eservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 60)
    private String password;

    private String matchingPassword;



}
