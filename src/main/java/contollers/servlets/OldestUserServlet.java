package contollers.servlets;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OldestUserServlet", value = "/oldestUser")
public class OldestUserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDAO = new UserDAO();
        userDAO.getOldestUserWithPhoneNumber();
        long count = userDAO.getUserCount();
        List<User> users = userDAO.getOldestUserWithPhoneNumber();
        req.setAttribute("count", count);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/users.jsp").forward(req,resp);
    }


}
