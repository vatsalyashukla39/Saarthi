package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    public List<Train> trainList;
    private static final String TRAINS_PATH = "../localDb/trains.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public TrainService() throws IOException {
        File trains = new File(TRAINS_PATH);
        this.trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }
    public List<Train> searchTrains(String sourceStation, String destinationStation){
        return trainList.stream().parallel().filter(train -> validTrain(train,sourceStation,destinationStation)).collect(Collectors.toList());
    }
    public boolean validTrain(Train train, String sourceStation, String destinationStation){
        List<String> stationOrder=train.getStations();
        int sourceIndex=stationOrder.indexOf(sourceStation.toLowerCase());
        int destinationIndex=stationOrder.indexOf(destinationStation.toLowerCase());
        return sourceIndex!=-1 && destinationIndex!=-1 && sourceIndex<destinationIndex;
    }
}
