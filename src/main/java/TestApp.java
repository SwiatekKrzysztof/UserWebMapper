import dao.UserDAO;
import hibernate.util.HibernateUtil;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FileService;
import service.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        performSomeTask();
////        User user =
////                new User("Keanu","Reeves", LocalDate.parse("1532-11-01"),"");
////        User user1 =
////                new User("Keanua","Reeves", LocalDate.parse("1532-11-01"),"");
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService();
        FileService fileService = new FileService();

////        userDAO.saveUser(user);
////        userDAO.saveUser(user1);
//
        List<String> testUsersLines = fileService
                .parseTextFile("C:\\Users\\48501\\Repositories\\UserWebMapper\\src\\main\\resources\\testUgly.csv");

        List<User> users = new ArrayList<>(userService.createUsers(testUsersLines));
        userDAO.saveUsers(users);

        //DAO query testing
        System.out.println("All Users");
        userDAO.getAllUsers().forEach(System.out::println);
        System.out.println("User Count = " + userDAO.getUserCount());

//        userDAO.deleteAllUsers();
//        System.out.println("Usuwanie");
//        userDAO.getAllUsers().forEach(System.out::println);
//
//
////        System.out.println("Oldest user");
////        System.out.println(userDAO.getOldestUserWithPhoneNumber());
////        System.out.println("Users by age");
////        userDAO.getAllUsersOrderedByAge().forEach(System.out::println);
////        System.out.println("Users with surname Reeves");
////        userDAO.getUserBySurname("Reeves").forEach(System.out::println);
        //


    }
    private static Logger logger = LogManager.getLogger(TestApp.class);
    public static void performSomeTask(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
    }
}
