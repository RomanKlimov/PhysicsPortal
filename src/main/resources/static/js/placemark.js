 ymaps.ready(init);

function init() {
    var myMap = new ymaps.Map("map", {
        center: [34.706195, 57.093033],
        zoom: 7
    }, {
        searchControlProvider: 'yandex#search'
    });
    
    myMap.geoObjects
        .add(new ymaps.Placemark([35.911082, 56.861860], {
            balloonContent: 'Тверь',
            iconCaption: '12 человек'
        }, {
            preset: 'islands#greenDotIconWithCaption'
        }));
    myMap.geoObjects
        .add(new ymaps.Placemark([34.329065, 56.262877], {
            balloonContent: 'Ржев',
            iconCaption: '5 человек'
        }, {
            preset: 'islands#greenDotIconWithCaption'
        }));


    var regionName = "Tver Oblast";

    // 1. Запрашиваем через геокодер район (у Яндекса этой возможности пока нет, придется пользоваться OSM)
    var url = "http://nominatim.openstreetmap.org/search";
    $.getJSON(url, {q: regionName, format: "json", polygon_geojson: 1})
        .then(function (data) {
            $.each(data, function(ix, place) {
                if ("relation" == place.osm_type) {
                    // 2. Создаем полигон с нужными координатами
                    var p = new ymaps.Polygon(place.geojson.coordinates);
                    // 3. Добавляем полигон на карту
                    myMap.geoObjects.add(p);
                }
            });
        }, function (err) {
            console.log(err);
        });
}
