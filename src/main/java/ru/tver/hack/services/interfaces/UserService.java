package ru.tver.hack.services.interfaces;

import ru.tver.hack.models.User;

import java.util.List;


public interface UserService {
    void saveUser(User user);

    void addSkillToUser(String skillName, User user);

    List<User> getUsersBySkills(String input);

    User getUserByEmail(String userEmail);

}
