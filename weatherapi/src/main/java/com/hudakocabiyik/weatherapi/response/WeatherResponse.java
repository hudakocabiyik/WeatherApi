package com.hudakocabiyik.weatherapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Sys sys;
    private String name;

    @Data
    public static class Weather {
        private int id;
        private String main;
        private String description;
    }


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
    public static class Wind {
        private double speed;
        private int deg;
    }

    @Data
    public static class Sys {
        private String country;
    }
}