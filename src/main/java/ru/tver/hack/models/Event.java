package ru.tver.hack.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "events")
@ToString(exclude = {"headOfEventUser"})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String name;
    private String description;
    private String link;

    @OneToOne
    private User headOfEventUser;

    //aka tags
    @ManyToMany
    private List<Skill> skills;

    //logo
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regions_id")
    private Region region;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> members;

    //presentation
    private String presentationUrl;


}
