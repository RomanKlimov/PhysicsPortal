package ru.tver.hack.services.interfaces;

import org.springframework.security.core.Authentication;
import ru.tver.hack.models.User;

public interface AuthService {
    User getUserByAuthentication(Authentication authentication);
}
