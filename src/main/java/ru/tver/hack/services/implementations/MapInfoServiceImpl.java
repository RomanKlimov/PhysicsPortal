package ru.tver.hack.services.implementations;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tver.hack.models.MapInfo;
import ru.tver.hack.repositories.MapInfoRepository;
import ru.tver.hack.services.interfaces.MapInfoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
        Stream<Object[]> allMapInfo = mapInfoRepository.findAllMapInfo();

//        if(Float.valueOf(strArray[0]) == map[0])
//      I need to get lat parametr of MapInfo model, using streams
        //ok, fuck streams, lets do it without   (streams the best)
//        allMapInfo.forEach(map -> {map[0]});
        //before add new , need do check if exist
        AtomicBoolean flag = new AtomicBoolean(true);

        allMapInfo.forEach(mi -> {
            if (flag.get() && mi[2].equals(Float.valueOf(strArray[0])) && mi[3].equals(Float.valueOf(strArray[1]))) {
                flag.compareAndSet(true,false);
            }
        });

        MapInfo mapInfo = MapInfo.builder()
                .city(city)
                .lat(Float.valueOf(strArray[0]))
                .lng(Float.valueOf(strArray[1]))
                .people(1)
                .build();

        if (flag.get()) {
            mapInfoRepository.save(mapInfo);
        }
        //incrementing people counter, need to get MapInfo by current city
        if (!flag.get()) {
            MapInfo mapByCity = mapInfoRepository.findByCity(city);
            int people = mapByCity.getPeople();
            mapByCity.setPeople(++people);
            mapInfoRepository.save(mapByCity);

        }

    }

    @Override
    public List<MapInfo> getAll() {
//        Stream<Object[]> allMapInfo = mapInfoRepository.findAllMapInfo();
//        List<MapInfo> list = new ArrayList<>();
//        Stream.of(allMapInfo)
////                .filter(mi -> mi instanceof MapInfo)
//                .map(mi -> (MapInfo) mi)
//                .forEach(mi -> list.add(mi));

//        allMapInfo.forEach(mi -> {
//            list.add((MapInfo) mi);
//        });

        return mapInfoRepository.findListMapInfo();
    }
}
