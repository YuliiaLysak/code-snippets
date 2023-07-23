package edu.lysak.userstore.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String role; // should be prefixed with ROLE_

    // constructors, getters and setters
}
