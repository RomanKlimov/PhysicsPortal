package ru.tver.hack.services.interfaces;

import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;

import java.util.List;


public interface ProjectService {
    void addNewProject(Project project, User user);
    Project getProjectByUuid(String uuid);
    void updateProject(Project project);

    List<Project> getProjectsByMember(User user);
}
