package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tver.hack.models.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findFirstByUuid(String uuid);
}
