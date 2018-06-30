package ru.tver.hack.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tver.hack.models.User;
import ru.tver.hack.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

   private UserRepository userRepository;

    private UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("User not found by <"+ email+">"));
        return new UserDetailsImpl(user);
    }
}
