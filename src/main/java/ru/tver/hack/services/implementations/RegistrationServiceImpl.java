package ru.tver.hack.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tver.hack.exceptions.EmailExistsException;
import ru.tver.hack.form.UserRegistrationForm;
import ru.tver.hack.models.User;
import ru.tver.hack.repositories.UserRepository;
import ru.tver.hack.security.Role.Role;
import ru.tver.hack.services.interfaces.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createUserAccount(UserRegistrationForm userRegistrationForm) throws EmailExistsException {
        if (!userRepository.findByEmailOrPhoneNumber(userRegistrationForm.getEmail(),userRegistrationForm.getPhoneNumber()).isPresent()){
            User user = User.builder()
                    .name(userRegistrationForm.getName())
                    .lastName(userRegistrationForm.getLastName())
                    .email(userRegistrationForm.getEmail())
                    .phoneNumber(userRegistrationForm.getPhoneNumber())
                    .city(userRegistrationForm.getCity())
                    .hashPassword(passwordEncoder.encode(userRegistrationForm.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
        } else throw new EmailExistsException("There is an account with that email address:" + userRegistrationForm.getEmail());
    }
}
