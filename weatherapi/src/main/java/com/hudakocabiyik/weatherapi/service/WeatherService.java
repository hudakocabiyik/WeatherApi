package com.hudakocabiyik.weatherapi.service;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hudakocabiyik.weatherapi.response.DailyWeatherResponse;
import com.hudakocabiyik.weatherapi.response.WeatherResponse;
import com.hudakocabiyik.weatherapi.response.WeeklyWeatherResponse;

@Service
public class WeatherService {

    private final String currentWeatherUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final String dailyWeatherUrl = "https://api.openweathermap.org/data/2.5/onecall";
    private final String weeklyWeatherUrl = "https://api.openweathermap.org/data/2.5/forecast";

    private String apiKey = "put your API_KEY to here";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getCurrentWeather(String country, String city) {
        String url = currentWeatherUrl + "?q=" + city + "," + country + "&appid=" + apiKey;
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    public DailyWeatherResponse getDailyWeather(String country, String city) {
        String url = dailyWeatherUrl + "?lat={lat}&lon={lon}&exclude=current,minutely,hourly,alerts&appid={apiKey}&units=metric";

        ResponseEntity<Map> response = restTemplate.getForEntity(
                currentWeatherUrl + "?q={city},{country}&appid={apiKey}&units=metric",
                Map.class, Map.of("city", city, "country", country, "apiKey", apiKey));
        Map<String, Double> coord = (Map<String, Double>) response.getBody().get("coord");
        double lat = coord.get("lat");
        double lon = coord.get("lon");

        DailyWeatherResponse dailyWeatherResponse = restTemplate.getForObject(url, DailyWeatherResponse.class, Map.of("lat", lat, "lon", lon, "apiKey", apiKey));
        return dailyWeatherResponse;
    }

    public WeeklyWeatherResponse getWeeklyWeather(String country, String city) {
        String url = weeklyWeatherUrl + "?q=" + city + "," + country + "&appid=" + apiKey;
        WeeklyWeatherResponse weeklyWeatherResponse = restTemplate.getForObject(url, WeeklyWeatherResponse.class);
        return weeklyWeatherResponse;
    }

}