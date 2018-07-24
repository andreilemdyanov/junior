package servlets;

import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class DeleteServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class DeleteServlet extends HttpServlet {

    private UserRepo users;

    public DeleteServlet() {
        this.users = new UserRepo();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.deleteUserWithEntities(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
