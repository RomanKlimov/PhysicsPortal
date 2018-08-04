package ru.tver.hack.models;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import ru.tver.hack.security.Role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@Builder
@ToString(exclude = {"projects"})
@EqualsAndHashCode
public class User {
    public User() {
        this.enabled = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "phoneNumber", unique = true)
    private Long phoneNumber;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Project> projects;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Event> events;

    @ManyToMany
    private List<Skill> skills;

    private String imageUrl;
}
