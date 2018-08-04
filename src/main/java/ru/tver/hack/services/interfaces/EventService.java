package ru.tver.hack.services.interfaces;

import org.springframework.stereotype.Service;
import ru.tver.hack.models.Event;
import ru.tver.hack.models.User;

import java.util.List;


public interface EventService {
    void addNewEvent(Event event, User user);
    Event getEventByUuid(String uuid);
    void updateEvent(Event event);

    List<Event> getProjectsByMember(User user);
}
