package contollers.servlets;

import dao.UserDAO;
import model.User;
import service.FileService;
import service.ServletService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ParseFileServlet", value = "/parse")
public class ParseFileServlet extends HttpServlet {
    private FileService fileService;
    private UserService userService;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        fileService = new FileService();
        userService = new UserService();
        userDAO = new UserDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("message").equals(ServletService.UPLOAD_CORRECT)) {
            String path = (String) req.getAttribute("path");
            List<String> lines = fileService.parseTextFile(path);
            List<User> users = userService.createUsers(lines);
            userDAO.saveUsers(users);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
