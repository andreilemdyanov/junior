package servlets;

import entities.MusicType;
import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class MusicTypesFindServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class MusicTypesFindServlet extends HttpServlet {

    private UserRepo users;

    public MusicTypesFindServlet() {
        this.users = new UserRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.findUserOnMusicType(
                MusicType.builder()
                        .name(req.getParameter("music"))
                        .build()));
        req.getRequestDispatcher("/WEB-INF/views/FindUserView.jsp").forward(req, resp);

    }

}
