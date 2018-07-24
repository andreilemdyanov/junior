package servlets;

import entities.Address;
import entities.MusicType;
import entities.Role;
import entities.User;
import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Class UpdateServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class UpdateServlet extends HttpServlet {

    private UserRepo users;

    public UpdateServlet() {
        this.users = new UserRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        boolean flag = false;
        User user = users.getUserDAO().get(req.getParameter("name"));
        if (user != null) {
            req.setAttribute("error", "Please, choose another login");
            doGet(req, resp);
            flag = true;
        }
        if (!flag) {
            List<String> list = new ArrayList<>(Arrays.asList(req.getParameterValues("interest")));
            List<MusicType> musicTypes = new ArrayList<>();
            for (String s : list) {
                MusicType musicType = users.getMusicTypeDAO().get(s);
                musicTypes.add(musicType);
            }
            users.updateUserWithEntities(
                    User.builder()
                            .id(Integer.parseInt(req.getParameter("id")))
                            .login(req.getParameter("login"))
                            .password(req.getParameter("password"))
                            .firstName(req.getParameter("firstName"))
                            .lastName(req.getParameter("lastName"))
                            .timeCreate(Calendar.getInstance())
                            .build(),
                    Role.builder()
                            .name(req.getParameter("role"))
                            .build(),
                    Address.builder()
                            .country(req.getParameter("country"))
                            .city(req.getParameter("city"))
                            .street(req.getParameter("street"))
                            .build(),
                    musicTypes);
            HttpSession session = req.getSession();
            session.setAttribute("login", req.getParameter("login"));
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
