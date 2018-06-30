package ru.tver.hack.services.interfaces;

import ru.tver.hack.exceptions.EmailExistsException;
import ru.tver.hack.form.UserRegistrationForm;
import ru.tver.hack.models.User;

public interface RegistrationService {
    void createUserAccount(UserRegistrationForm userRegistrationForm) throws EmailExistsException;
}
