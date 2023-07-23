package edu.lysak.userstore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

//    @GetMapping("/username")
//    public void username(Authentication auth) {
//        System.out.println(auth.getName());
//    }

    @GetMapping("/username")
    public void username(@AuthenticationPrincipal UserDetails details) {
        System.out.println(details.getUsername());
    }

    @GetMapping("/details")
    public void details(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();

        System.out.println("Username: " + details.getUsername());
        System.out.println("User has authorities/roles: " + details.getAuthorities());
    }
}
