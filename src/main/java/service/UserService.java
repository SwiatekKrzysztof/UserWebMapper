package service;

import model.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> createUsers(List<String> linesList) {
        List<User> users = new ArrayList<>();
        String[] line;
        for (int i = 0; i < linesList.size(); i++) {
            line = linesList.get(i).split(",");
            if(line.length<3){
                continue;
            }
            User newUser = new User();
            newUser.setName(line[0]);
            newUser.setSurname(line[1]);
            newUser.setBirthDate(LocalDate.parse(line[2]));
            if (line.length < 4) {
                newUser.setPhoneNumber("UNKNOWN");
            } else {
                newUser.setPhoneNumber(line[3]);
            }
            users.add(newUser);
        }
        return users;
    }
    public void printUsers(List<User> users){
        for (User user:users){
            System.out.println("Name:        "+ user.getName());
            System.out.println("Surname:     "+ user.getSurname());
            System.out.println("Age:         "+ user.getAge());
            System.out.println("Phone Number:"+ user.getPhoneNumber());
            System.out.println();
        }
    }
    public static int calculateUserAge(User user){
        return ((int) ChronoUnit.YEARS.between(user.getBirthDate(),LocalDate.now()));
    }
}
