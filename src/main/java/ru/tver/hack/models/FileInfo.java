package ru.tver.hack.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "files")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String type;
    private String url;
}
