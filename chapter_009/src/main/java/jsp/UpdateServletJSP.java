package jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class UpdateServletJSP.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 21.04.2018
 */
public class UpdateServletJSP extends HttpServlet {
    private UserStore users;

    @Override
    public void init() throws ServletException {
        users = UserStore.INSTANCE;
        users.createTable();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.updateUser(req.getParameter("loginForUp"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
