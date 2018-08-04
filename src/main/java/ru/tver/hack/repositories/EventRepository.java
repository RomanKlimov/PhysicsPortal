package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tver.hack.models.Event;
import ru.tver.hack.models.User;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findFirstByUuid(String uuid);
    List<Event> findAllByMembersContaining(User user);
}
