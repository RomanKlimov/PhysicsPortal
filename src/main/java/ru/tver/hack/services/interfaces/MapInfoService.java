package ru.tver.hack.services.interfaces;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public interface MapInfoService {
    void addCity(String city) throws InterruptedException, IOException, UnirestException;
}
