package Service;

import Model.Booking;
import Model.Train;
import Model.TrainSystem;
import Model.User;

public class TrainBookingService {
     TrainSystem trainSys;
     public TrainBookingService(TrainSystem trainSys){
         this.trainSys=trainSys;
     }
    User currentUser=null;
    public  void login(String uName,String password){
        for(User user:trainSys.getUsers().values()){
            if(user.getName().equals(uName)&& user.getPassword().equals(password)){
                currentUser=user;
                System.out.println("Login Successfull");
                System.out.println("Welcome"+" "+user.getName());
                return;
            }
            else{
                System.out.println("Invalid Credentials");
            }
        }
    }
    public void logout(){
        if(currentUser==null){
            System.out.println("No Login Found");
            return;
        }
        else{
            currentUser=null;
            System.out.println("Logged out Successfully");
        }

    }
    public void checkTrain(int id,String name){
        if(currentUser==null){
            System.out.println("Login to Continue");
            return;
        }
        Train train=trainSys.getTrains().get(id);
        if(train==null){
            System.out.println("No Train Found");
            return;
        }
        System.out.println(train.isAvailability());
    }
    public void bookTicket(int trainId,int count){
        if(currentUser==null){
            System.out.println("Login First");
            return;
        }
        Train train=trainSys.getTrains().get(trainId);
        if(train == null){
            System.out.println("Train not found");
            return;
        }
        if(train.getTotalSeats() < count){
            System.out.println("Not enough seats");
            return;
        }
        Booking booking= new Booking();
        booking.setBookingId(trainSys.getBookings().size()+1);
        booking.setUserId(currentUser.getUser_id());
        booking.setTrainId(trainId);
        booking.setSeatCount(count);
        booking.setBookingStatus(true);
        trainSys.getBookings().put(booking.getBookingId(), booking);

        train=new Train(train.getTrainId(),train.getTrainName(),train.getFromStat(),
                train.getToStat(),
                train.getTotalSeats()-count,true);
        trainSys.getTrains().put(trainId,train);
        System.out.println("Booking Successfull. Booking Id:"+booking.getBookingId());
    }
    public void cancelBooking(int bid){
        Booking booking=trainSys.getBookings().get(bid);
        if(booking==null||!booking.isBookingStatus()){
            System.out.println("Invalid Booking");
            return;
        }
        booking.setBookingStatus(false);
        Train train=trainSys.getTrains().get(booking.getTrainId());
        train=new Train(
                train.getTrainId(),
                train.getTrainName(),
                train.getFromStat(),
                train.getToStat(),
                train.getTotalSeats() + booking.getSeatCount(),
                true
        );
        trainSys.getTrains().put(train.getTrainId(),train);
        System.out.println("Booking Cancelled");
    }
    public void addUser(int uid, String name ,String passkey){
        User user=new User();
        user.setUser_id(uid);
        user.setName(name);
        user.setPassword(passkey);
        trainSys.getUsers().put(uid,user);
        System.out.println("User Added Successfully");
    }
    public void addTrain(int id, String name,String from ,String to,int seats){
        Train train=new Train(id,name,from,to,seats,true);
        trainSys.getTrains().put(id,train);
        System.out.println("Train Added Successfully");
    }

    public void getBookingDetails(){
        for(Booking b:trainSys.getBookings().values()){
            System.out.println("Booking Id:"+b.getBookingId()+
                    "trainId:"+b.getTrainId()+
                    "Seats:"+b.getSeatCount()+
                    "Status:"+b.isBookingStatus());
        }
    }
    public void getUserHistory(){
        if(currentUser==null){
            System.out.println("Login First");
            return;
        }
        for(Booking b:trainSys.getBookings().values()){
             if(b.getUserId()== currentUser.getUser_id()){
                 System.out.println("BookingId:"+b.getBookingId()+
                         "TrainId:"+b.getTrainId()+
                          "Seats:"+b.getSeatCount());
             }
        }
    }
}
