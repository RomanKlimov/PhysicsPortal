package ru.tver.hack.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.tver.hack.models.User;
import ru.tver.hack.repositories.UserRepository;
import ru.tver.hack.services.interfaces.AuthService;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> userOptional = userRepository.findOneByEmail(username);
        return userOptional.orElse(null);
    }
}
