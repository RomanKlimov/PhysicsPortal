package ru.tver.hack.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tver.hack.models.Event;
import ru.tver.hack.models.User;
import ru.tver.hack.repositories.EventRepository;
import ru.tver.hack.services.interfaces.EventService;
import ru.tver.hack.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public void addNewEvent(Event event, User user) {
        event.setUuid(UUID.randomUUID().toString());
        event.setHeadOfEventUser(user);
        user.getEvents().add(event);
        eventRepository.save(event);
        userService.saveUser(user);

    }

    @Override
    public Event getEventByUuid(String uuid) {
        return eventRepository.findFirstByUuid(uuid);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);

    }

    @Override
    public List<Event> getNearEvents() {
        List<Event> allOrderedByDate = eventRepository.findAllByOrderByDate();
        java.util.Date date=new java.util.Date();
        List<Event> nearEvent = new ArrayList<>();
        for(Event event : allOrderedByDate){
            if(event.getDate().compareTo(date) > 0) {
                nearEvent.add(event);
            }
        }
        //getting first 3 nearest events
        return nearEvent.stream().limit(3).collect(Collectors.toList());
    }

    @Override
    public List<Event> getProjectsByMember(User user) {
        return eventRepository.findAllByMembersContaining(user);
    }
}
