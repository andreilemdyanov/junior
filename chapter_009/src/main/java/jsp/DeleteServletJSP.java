package jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class DeleteServletJSP.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class DeleteServletJSP extends HttpServlet {

    private UserStore users;

    @Override
    public void init() throws ServletException {
        users = UserStore.INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.deleteUser(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
