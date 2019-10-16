package contollers.servlets;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SortUsersByAgeServlet", value = "/usersAgeSort")
public class SortUsersByAgeServlet extends HttpServlet {
    UserDAO userDAO;
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsersOrderedByAge();
        long count = userDAO.getUserCount();
        req.setAttribute("users",users);
        req.setAttribute("count",count);
        req.getRequestDispatcher("/users.jsp").forward(req,resp);
    }


}
