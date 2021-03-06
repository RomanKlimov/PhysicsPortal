package ru.tver.hack.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
//    @JoinTable(name="even_members",
//                joinColumns = { @JoinColumn(name = "event_id") },
//            inverseJoinColumns = { @JoinColumn(name = "member_id") })
    private List<User> members;

    //presentation
    private String presentationUrl;

    private Date date;


}
