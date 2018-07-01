package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findFirstByUuid(String uuid);
    List<Project> findAllByMembersContaining(User user);
}
