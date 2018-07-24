package servlets;

import entities.Address;
import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class AddressFindServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class AddressFindServlet extends HttpServlet {

    private UserRepo users;

    public AddressFindServlet() {
        this.users = new UserRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.findUserOnAddress(
                Address.builder()
                        .country(req.getParameter("country"))
                        .city(req.getParameter("city"))
                        .street(req.getParameter("street"))
                        .build()));
        req.getRequestDispatcher("/WEB-INF/views/FindUserView.jsp").forward(req, resp);

    }

}
