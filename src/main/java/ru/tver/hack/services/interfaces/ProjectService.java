package ru.tver.hack.services.interfaces;

import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;


public interface ProjectService {
    void addNewProject(Project project, User user);
    Project getProjectUuid(String uuid);
    void updateProject(Project project);
}
