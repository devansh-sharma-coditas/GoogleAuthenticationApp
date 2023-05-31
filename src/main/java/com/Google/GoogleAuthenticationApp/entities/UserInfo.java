package com.Google.GoogleAuthenticationApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String profilePath;
    private String loginVia;
}
