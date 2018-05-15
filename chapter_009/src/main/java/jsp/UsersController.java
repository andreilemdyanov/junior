package jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class UsersController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class UsersController extends HttpServlet {

    private UserStore users;

    @Override
    public void init() throws ServletException {
        users = UserStore.INSTANCE;
        users.createTable();
        users.createRoles();
        users.createUser("root", "root", "root", "root", 2);
        users.createUser("admin", "admin", "admin", "admin", 1);
        users.createUser("some", "some", "some", "some", 2);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.createUser(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), Integer.valueOf(req.getParameter("select")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
