package servlets;

import entities.Role;
import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class RoleFindServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class RoleFindServlet extends HttpServlet {

    private UserRepo users;

    public RoleFindServlet() {
        this.users = new UserRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.findUserOnRole(
                Role.builder()
                        .name(req.getParameter("role"))
                        .build()));
        req.getRequestDispatcher("/WEB-INF/views/FindUserView.jsp").forward(req, resp);

    }

}
