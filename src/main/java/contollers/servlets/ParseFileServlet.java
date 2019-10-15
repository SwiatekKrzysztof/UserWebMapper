package contollers.servlets;

import dao.UserDAO;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FileService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ParseFileServlet", value = "/parse")
public class ParseFileServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserService.class);
    FileService fileService;
    UserService userService;
    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        fileService = new FileService();
        userService = new UserService();
        userDAO = new UserDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = (String) req.getAttribute("path");
        List<String> lines = fileService.parseTextFile(path);
        List<User> users = userService.createUsers(lines);
//        Connection conn = (Connection) getServletContext().getAttribute("connection");
//        conn.
        userDAO.saveUsers(users);
        User keeanu =
                new User("Keanu", "Reeves", LocalDate.parse("1532-11-01"), "");
        userDAO.saveUser(keeanu);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/users").forward(req, resp);
    }
}
