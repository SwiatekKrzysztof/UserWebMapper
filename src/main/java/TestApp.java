import dao.UserDAO;
import hibernate.util.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.FileService;
import service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        User user =
                new User("Keanu","Reeves", LocalDate.parse("1532-11-01"),"666666667");
        User user1 =
                new User("Keanu","Reeves", LocalDate.parse("1532-11-01"),"666666668");
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService();
        FileService fileService = new FileService();

        userDAO.saveUser(user);
        userDAO.saveUser(user1);

        List<String> testUsersLines = fileService.parseTextFile("C:\\Users\\48501\\TestFolder\\test.txt");

        List<User> users = new ArrayList<>(userService.createUsers(testUsersLines));
//        users.forEach(u -> userDAO.saveUser(u));
        userDAO.saveUsers(users);

        List<User> allUsers = userDAO.getAllUsers();
        allUsers.forEach(System.out::println);
    }
}
