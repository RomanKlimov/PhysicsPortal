package ru.tver.hack.services.interfaces;

import com.mashape.unirest.http.exceptions.UnirestException;
import ru.tver.hack.models.MapInfo;

import java.io.IOException;
import java.util.List;

public interface MapInfoService {
    void addCity(String city) throws InterruptedException, IOException, UnirestException;

    List<MapInfo> getAll();
}
