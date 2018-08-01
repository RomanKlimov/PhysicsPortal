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
@Table(name = "projects")
@ToString(exclude = {"headOfProjectUser"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private String link;
    private String contacts;

    @OneToOne
    private User headOfProjectUser;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> applicants;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> members;
    private String presentationFile;

    //aka tags
    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private List<Resource> resources;

    //logo
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regions_id")
    private Region region;

    private String space;

    //presentation
    private String presentationUrl;

    private String patrion;

    private Boolean agreement = false;
}
