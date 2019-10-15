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
        String[] dateLine;
        for (int i = 0; i < linesList.size(); i++) {
            line = linesList.get(i).split(";");

            if(line.length<3){
                // todo log
                continue;
            }
            User newUser = new User();
            newUser.setName(line[0]);
            newUser.setSurname(line[1]);

            dateLine = line[2].split("\\.");
            if(dateLine.length!=3){
                //todo log
                continue;
            }
            newUser.setBirthDate(LocalDate.of(
                    Integer.parseInt(dateLine[0]),
                    Integer.parseInt(dateLine[1]),
                    Integer.parseInt(dateLine[2])));

            if (line.length < 4) {
                newUser.setPhoneNumber(null);
            } else {
                newUser.setPhoneNumber(line[3]);
            }
            newUser = normalizeUser(newUser);
            if(validateUser(newUser)) {
                users.add(newUser);
            }
        }
        return users;
    }

    private boolean validateUser(User user) {
        if(null != user.getPhoneNumber()){
            if(user.getPhoneNumber().length()!=9)
            return false;
        }
        if(user.getName().equals("") || user.getSurname().equals("")){
            return false;
        }
        return true;
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
    private User normalizeUser(User user){
        User userCopy = new User(user);
        userCopy.setName
                (normalizeName(userCopy.getName()));
        userCopy.setSurname
                (normalizeSurname(userCopy.getSurname()));
        if(null!=userCopy.getPhoneNumber())
        userCopy.setPhoneNumber
                (normalizePhoneNumber(userCopy.getPhoneNumber()));
        return userCopy;
    }
    private String normalizeName(String name){
        name = removeSpecialCharacters(name);
        if(!name.isEmpty()) {
            name = name.toLowerCase();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return name;
    }
    private String normalizeSurname(String surname){
        return normalizeName(surname);
    }
    public String normalizePhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("[^0-9]","");
        if(phoneNumber.equals("")){
            phoneNumber=null;
        }
        return phoneNumber;
    }
    private String removeSpecialCharacters(String string){
        return string.replaceAll("([^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ])","");
    }
}
