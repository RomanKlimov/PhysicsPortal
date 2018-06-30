package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tver.hack.models.Skill;
import ru.tver.hack.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
    Optional<User> findByEmailOrPhoneNumber(String email, Long phoneNumber);
    Optional<User> findByEmailAndEnabled(String email, Boolean t);
    List<User> findAllBySkillsContaining(Skill skill);
}
