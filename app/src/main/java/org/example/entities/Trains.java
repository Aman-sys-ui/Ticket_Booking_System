package org.example.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Trains {
    private String trainId;
    private Map<String,String> stationTime;
    private List<List<String>> seats;
    private String trainNo;
    private List<String> stations ;
//    private User user;
    //private List<String> stations = new ArrayList<>(Arrays.asList("pune","pimpri","saharsa","nanded"));

    public Trains() {

    }

    public Trains(  String trainId, List<String> stations, Map<String, String> stationTime, List<List<String>> seats, String trainNo) {
        this.trainId = trainId;
        this.stations = stations;
        this.stationTime = stationTime;
        this.seats = seats;
        this.trainNo = trainNo;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public Map<String, String> getStationTime() {
        return stationTime;
    }

    public void setStationTime(Map<String, String> stationTime) {
        this.stationTime = stationTime;
    }

    public List<List<String>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<String>> seats) {
        this.seats = seats;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }
    
}
