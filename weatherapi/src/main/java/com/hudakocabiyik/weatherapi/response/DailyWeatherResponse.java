package com.hudakocabiyik.weatherapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyWeatherResponse {
    private List<DailyWeather> daily;

    @Data
    public static class DailyWeather {
        private long dt;
        private Temp temp;
        private List<Weather> weather;

        @Data
        public static class Temp {
            private double day;
            private double min;
            private double max;
            private double night;
            private double eve;
            private double morn;
        }

        @Data
        public static class Weather {
            private int id;
            private String main;
            private String description;
            private String icon;
        }
    }
}