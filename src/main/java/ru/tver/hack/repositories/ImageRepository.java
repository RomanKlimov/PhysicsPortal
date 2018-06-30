package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tver.hack.models.ImageInfo;

import java.util.Optional;


public interface ImageRepository extends JpaRepository<ImageInfo, Long> {
    ImageInfo findFirstByUid(Long id);
    Optional<ImageInfo> findFirstByStorageFileName(String name);
}
