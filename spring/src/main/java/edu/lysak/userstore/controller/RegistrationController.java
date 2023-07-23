package edu.lysak.userstore.controller;

import edu.lysak.userstore.domain.User;
import edu.lysak.userstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        // input validation omitted for brevity

        user.setPassword(encoder.encode(user.getPassword()));

        userRepo.save(user);
    }
}
