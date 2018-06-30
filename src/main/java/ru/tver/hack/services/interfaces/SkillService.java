package ru.tver.hack.services.interfaces;

import ru.tver.hack.models.Skill;

import java.util.List;
import java.util.Optional;


public interface SkillService {
    List<Skill> getSkillsByNameLike(String name);
    Optional<Skill> getSkillByName(String skillName);
    void addSkill(Skill skill);
}
