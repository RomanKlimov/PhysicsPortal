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
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import ru.tver.hack.models.MapInfo;
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
import java.util.List;
import java.util.stream.Stream;

@Service
public class MapInfoServiceImpl implements MapInfoService {

    @Autowired
    MapInfoRepository mapInfoRepository;

    @Transactional
    @Override
    public void addCity(String city) throws InterruptedException, IOException, UnirestException {

        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://geocode-maps.yandex.ru/1.x/?geocode=" + city + "&format=json")
                .asJson();
        // retrieve the parsed JSONObject from the response
        JSONObject jsonObj = jsonResponse.getBody().getObject();

        String position = jsonObj.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(0).getJSONObject("GeoObject").getJSONObject("Point").getString("pos");
        String strArray[] = position.split(" ");

//        Stream<Object[]> allMapInfo = mapInfoRepository.findAllMapInfo();
        List<MapInfo> allMapInfo = mapInfoRepository.findAllMapInfo();

//        if(Float.valueOf(strArray[0]) == map[0])
//      I need to get lat parametr of MapInfo model, using streams
        //ok, fuck streams, lets do it without
//        allMapInfo.forEach(map -> {map[0]});
        //before add new , need do check if exist
        boolean flag = true;
        for(MapInfo mi : allMapInfo) {
            if (mi.getLat() == Float.valueOf(strArray[0])) {
                if (mi.getLng() == Float.valueOf(strArray[1])) {
                    flag = false;
                    break;
                }
            }
        }

        MapInfo mapInfo = MapInfo.builder()
                .city(city)
                .lat(Float.valueOf(strArray[0]))
                .lng(Float.valueOf(strArray[1]))
                .people(1)
                .build();

        if (flag) {
            mapInfoRepository.save(mapInfo);
        }
        //incrementing people counter, need to get MapInfo by current city
        if (!flag) {
            MapInfo mapByCity = mapInfoRepository.findByCity(city);
            int people = mapByCity.getPeople();
            people++;
            mapByCity.setPeople(people);
            mapInfoRepository.save(mapByCity);

        }

    }
}
