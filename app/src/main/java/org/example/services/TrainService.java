package org.example.services;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Trains;
import org.example.entities.User;

import java.io.IOException;
import java.util.*;

import java.io.File;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TrainService {
    private Trains trains;
    private ObjectMapper objectMapper;
    private List<Trains> trainList;
    private static final String PATH_TO_TRAINSFILE = "app/src/main/java/org/example/localDb/trains.json";
    private User user;

//    public TrainService(User user) throws IOException{
//        loadTrainsFile();
//        this.user = user;
//        System.out.println(user.getName());  // todo fix here null is printing
//    }

    public TrainService(Trains trains) throws IOException{
        this.trains = trains;
        loadTrainsFile();
    }

    public TrainService() throws IOException{
        loadTrainsFile();
    }

    public void loadTrainsFile() throws IOException {
        objectMapper = new ObjectMapper();
        trainList = objectMapper.readValue(new File(PATH_TO_TRAINSFILE), new TypeReference<List<Trains>>() {});
    }


    public List<Trains> searchTrains(User LoginUser,String source,String destination){
        System.out.println(LoginUser.getName() + "is searching for trains"); // todo fix here null is printing
        return trainList.stream().filter(train ->isTrainAvailable(train,source,destination)).collect(Collectors.toList());
    }

    public boolean isTrainAvailable(Trains trains,String source,String destination){
        int sourceIndex = trains.getStations().indexOf(source.toLowerCase());
        int destinationIndex = trains.getStations().indexOf(destination.toLowerCase());
        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

    public void addTrains(String stationDetails,List<List<String>> seats,String trainNo) throws IOException {
        List<Object> station = refineStationDetails(stationDetails);
        LinkedHashMap<String,String> station1 =(LinkedHashMap<String,String>)station.get(1);
        Trains train = new Trains(UUID.randomUUID().toString(),(List<String>)station.getFirst(), station1,seats,trainNo);
        objectMapper = new ObjectMapper();
        trainList.add(train);
        File trainFile = new File(PATH_TO_TRAINSFILE);
        objectMapper.writeValue(trainFile,trainList);
        System.out.println(train.getTrainNo() + " added");
    }

    public List<Object> refineStationDetails(String input){
        List<Object> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        String[] stationData = input.trim().split(";");
        Map<String , String> station = new LinkedHashMap<>();
        for(String data : stationData) {
            String[] stationEntries = data.trim().split(",");
            if (stationEntries.length == 2) {
                String stationName = stationEntries[0].trim();
                list.add(stationName);
                String stationTime = stationEntries[1].trim();
                station.put(stationName, stationTime);
            }
        }
        result.add(list);
        result.add(station);
        return result;
    }

    public List<Trains> fetchTrain(String trainId){
        return trainList.stream().filter(train ->trainId.equals(train.getTrainId())).collect(Collectors.toList());
    }


}


