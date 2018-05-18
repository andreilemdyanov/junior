package jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class UpdateServletJSP.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class UpdateServletJSP extends HttpServlet {

    private UserStore users;

    public UserStore getUsers() {
        return users;
    }

    public UpdateServletJSP() {
        this.users = UserStore.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.updateUser(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
        HttpSession session = req.getSession();
        synchronized (session) {
            session.setAttribute("login", req.getParameter("login"));
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
