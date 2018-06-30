package ru.tver.hack.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tver.hack.models.Skill;
import ru.tver.hack.repositories.SkillRepository;
import ru.tver.hack.services.interfaces.SkillService;

import java.util.List;
import java.util.Optional;

/**
 * Date 22.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> getSkillsByNameLike(String name) {
        return skillRepository.findAllBySkillNameIsLike(name);
    }

    @Override
    public Optional<Skill> getSkillByName(String skillName){
         return skillRepository.findFirstBySkillName(skillName);
    }

    @Override
    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }
}
