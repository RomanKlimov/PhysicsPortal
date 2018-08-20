package ru.tver.hack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tver.hack.models.MapInfo;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface MapInfoRepository extends JpaRepository<MapInfo, Long> {
    @Query(value = "SELECT * FROM map", nativeQuery = true)
    Stream<Object[]> findAllMapInfo();
}