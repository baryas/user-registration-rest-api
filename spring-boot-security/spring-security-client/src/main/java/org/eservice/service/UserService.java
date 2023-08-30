package org.eservice.service;

import org.eservice.entity.User;
import org.eservice.entity.VerificationToken;
import org.eservice.model.UserModel;
import org.eservice.repository.UserRepository;
import org.eservice.repository.VerificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private VerificationTokenRepository verificationTokenRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       VerificationTokenRepository verificationTokenRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public User registerUser(UserModel userModel){
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
       // user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setPassword(userModel.getPassword());
        user.setRole("USER");

        userRepository.save(user);

        return user ;

    }

    public void saveVerificationForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(token,user);
        verificationTokenRepository.save(verificationToken);

    }
}
