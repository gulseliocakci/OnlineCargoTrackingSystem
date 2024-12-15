package org.example;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String cityName;
    private String cityID;
    private List<City> connectedCities;

    public City(String cityName, String cityID) {
        this.cityName = cityName;
        this.cityID = cityID;
        this.connectedCities = new ArrayList<>();
    }

    public void addConnectedCity(City city) {
        connectedCities.add(city);
    }

    public String getCityName() {
        return cityName;
    }

    public List<City> getConnectedCities() {
        return connectedCities;
    }

    @Override
    public String toString() {
        return "City: " + cityName + " (ID: " + cityID + ")";
    }
}
