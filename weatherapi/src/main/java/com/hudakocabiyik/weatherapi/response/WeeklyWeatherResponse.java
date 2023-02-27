package com.hudakocabiyik.weatherapi.response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyWeatherResponse {
    @JsonProperty("list")
    private List<WeatherList> weatherList;

    @JsonProperty("city")
    private City city;

    @Data
    public static class WeatherList {
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private Sys sys;
        private String dt_txt;


        public static class Main {
            private double temp;
            private int pressure;
            private int humidity;

            private double feels_like;
            private double temp_min;
            private double temp_max;

            public float getTemp() {
                return Math.round(temp-273.15);
            }

            public float getFeels_like() {
                return Math.round(feels_like-273.15);
            }

            public float getTemp_min() {
                return Math.round(temp_min-273.15);
            }

            public float getTemp_max() {
                return Math.round(temp_max-273.15);
            }

            public int getPressure() {
                return pressure;
            }

            public int getHumidity() {
                return humidity;
            }
        }

        @Data
        public static class Weather {
            private int id;
            private String main;
            private String description;
            private String icon;
        }

        @Data
        public static class Clouds {
            private int all;
        }

        @Data
        public static class Wind {
            private double speed;
            private int deg;
        }

        @Data
        public static class Sys {
            private String pod;
        }
    }

    @Data
    public static class City {
        private int id;
        private String name;
        private Coord coord;
        private String country;
        private int timezone;
        private int sunrise;
        private int sunset;

        @Data
        public static class Coord {
            private double lat;
            private double lon;
        }
    }
}