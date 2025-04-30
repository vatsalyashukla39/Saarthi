package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private static final String USERS_PATH = "../localDb/users.json";
    private ObjectMapper objectMapper=new ObjectMapper();
    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }
    public UserBookingService() throws IOException {
        this.user = null;
        loadUserListFromFile();
    }
    public void loadUserListFromFile() throws IOException {
        File users = new File(USERS_PATH);
        this.userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }
    public Boolean loginUser(){
        if (userList == null) {
            return Boolean.FALSE;
        }
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) 
                && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean signUp(User user){
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
    private void saveUserListToFile() throws IOException {
        File usersFile=new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }
    public void fetchBookingList(){
        user.printTickets();
    }
    public Boolean cancelBooking(String ticketId){
       try{
        List<Ticket> l2=user.getTicketsBooked();
        List<Ticket> l1=l2.stream().filter(ticket -> (!ticket.getTicketId().equalsIgnoreCase(ticketId))).collect(Collectors.toList());
        user.setTicketsBooked(l1);
        saveUserListToFile();
        return l1.size()!=l2.size();}
       catch (IOException ex){
           return Boolean.FALSE;
       }
    }
    public List<Train> getTrains(String sourceStation, String destinationStation){
        try{
            TrainService trainService=new TrainService();
            return trainService.searchTrains(sourceStation,destinationStation);
        }catch (IOException ex){
            return null;
        }
    }
//    public boolean bookTickets(Train train) {
//        try {
//            if (train.getAvailableSeats() >= passengerCount) {
//                List<Ticket> tickets = train.bookSeats(user, passengerCount);
//                user.getTicketsBooked().addAll(tickets);
//                train.setAvailableSeats(train.getAvailableSeats() - passengerCount);
//                saveUserListToFile();
//                return true;
//            } else {
//                return false;
//            }
//        } catch (IOException ex) {
//            return false;
//        }
//    }
}
