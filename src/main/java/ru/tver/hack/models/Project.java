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
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private String link;
    private String contacts;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;
    private String presentationFile;
}
