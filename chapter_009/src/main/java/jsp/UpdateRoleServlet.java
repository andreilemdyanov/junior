package jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class UpdateRoleServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class UpdateRoleServlet extends HttpServlet {

    private UserStore users;

    public UserStore getUsers() {
        return users;
    }

    public UpdateRoleServlet() {
        this.users = UserStore.INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("role").equals("ADMIN")) {
            if (Integer.valueOf(req.getParameter("select")) == 1) {
                users.updateRole(Integer.parseInt(req.getParameter("id")), 1);
            } else if (Integer.valueOf(req.getParameter("select")) == 2) {
                users.updateRole(Integer.parseInt(req.getParameter("id")), 2);
            }
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }
}
