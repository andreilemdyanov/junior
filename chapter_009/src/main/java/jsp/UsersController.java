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

    public UserStore getUsers() {
        return users;
    }

    public UsersController() {
        this.users = UserStore.INSTANCE;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users", users.getAllUsers());

        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        boolean flag = false;
        User user = users.getUser(req.getParameter("name"), req.getParameter("login"));
        if (user != null) {
            req.setAttribute("error", "Please, choose another name and login");
            doGet(req, resp);
            flag = true;
        }
        if (!flag) {
            users.createUser(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"), Integer.valueOf(req.getParameter("role")), req.getParameter("country"), req.getParameter("city"));
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
