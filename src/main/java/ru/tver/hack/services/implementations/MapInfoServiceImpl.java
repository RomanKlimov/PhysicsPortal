package ru.tver.hack.services.implementations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import ru.tver.hack.repositories.MapInfoRepository;
import ru.tver.hack.services.interfaces.MapInfoService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

@Service
public class MapInfoServiceImpl implements MapInfoService {

    @Autowired
    MapInfoRepository mapInfoRepository;

    @Override
    public void addCity(String city) throws InterruptedException, IOException, UnirestException {

        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://geocode-maps.yandex.ru/1.x/?geocode=" + city + "&format=json")
                .asJson();
        // retrieve the parsed JSONObject from the response

        JSONObject jsonObj = jsonResponse.getBody().getObject();

        System.out.println(jsonObj.toString());
        String position = jsonObj.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(1).getJSONObject("GeoObject").getJSONObject("Point").getString("pos");
        String strArray[] = position.split(" ");

//        Stream<Object[]> allMapInfo = mapInfoRepository.findAllMapInfo();
//
//        allMapInfo.forEach(map -> );
    }
}
