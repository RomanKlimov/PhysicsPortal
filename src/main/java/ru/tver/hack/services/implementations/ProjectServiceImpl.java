package ru.tver.hack.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tver.hack.models.Project;
import ru.tver.hack.models.User;
import ru.tver.hack.repositories.ProjectRepository;
import ru.tver.hack.services.interfaces.ProjectService;
import ru.tver.hack.services.interfaces.UserService;

import java.util.UUID;

/**
 * Date 23.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void addNewProject(Project project, User user) {
        project.setUuid(UUID.randomUUID().toString());
        project.setHeadOfProjectUser(user);
        user.getProjects().add(project);
        projectRepository.save(project);
        userService.saveUser(user);
    }

    @Override
    public Project getProjectUuid(String uuid) {
        return projectRepository.findFirstByUuid(uuid);
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.save(project);
    }
}
