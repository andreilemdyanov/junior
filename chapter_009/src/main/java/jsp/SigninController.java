package jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class SigninController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class SigninController extends HttpServlet {

    private UserStore users;

    public UserStore getUsers() {
        return users;
    }

    public SigninController() {
        this.users = UserStore.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (users.isCredentional(login, password) == 1 || users.isCredentional(login, password) == 2) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                if (users.isCredentional(login, password) == 1) {
                    session.setAttribute("role", "ADMIN");
                } else {
                    session.setAttribute("role", "DEFAULT");
                }
            }
            resp.sendRedirect(String.format("%s", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentional invalid");
            doGet(req, resp);
        }
    }
}
