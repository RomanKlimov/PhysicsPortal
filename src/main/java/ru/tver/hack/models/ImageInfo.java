package ru.tver.hack.models;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Builder
@Entity
@Table(name = "images")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String storageFileName;
    private String url;
    private Long uid;
}
