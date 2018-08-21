package ru.tver.hack.models;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "map")
@AllArgsConstructor
@Builder
@ToString()
@EqualsAndHashCode
@NoArgsConstructor
public class MapInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private float lat;
    private float lng;
    //counter of registered people in this area
    private int people;

}
