package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tver.hack.models.Skill;

import java.util.List;
import java.util.Optional;


public interface SkillRepository extends JpaRepository<Skill, Long>{
    List<Skill> findAllBySkillNameLike(String skillName);
    List<Skill> findAllBySkillNameIsLike(String skillName);
    Optional<Skill> findFirstBySkillName(String skillName);
}
