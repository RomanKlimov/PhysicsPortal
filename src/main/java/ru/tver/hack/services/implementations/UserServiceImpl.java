package ru.tver.hack.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tver.hack.models.Skill;
import ru.tver.hack.models.User;
import ru.tver.hack.repositories.UserRepository;
import ru.tver.hack.services.interfaces.SkillService;
import ru.tver.hack.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Date 23.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public void addSkillToUser(String skillName, User user) {
        Optional<Skill> skillOptional = skillService.getSkillByName(skillName);
        if (skillOptional.isPresent()){
            List<Skill> skills = user.getSkills();
            skills.add(skillOptional.get());
            userRepository.save(user);
        }else throw new IllegalArgumentException("skill by name: <" + skillName + "> not found!");
    }

    @Override
    @Transactional
    public List<User> getUsersBySkills(String input) {
        Optional<Skill> skillOptional = skillService.getSkillByName(input);
        if (skillOptional.isPresent()){
            Skill skill = skillOptional.get();
            return userRepository.findAllBySkillsContaining(skill);
        }else return null;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.findOneByEmail(userEmail).orElse(null);
    }
}
