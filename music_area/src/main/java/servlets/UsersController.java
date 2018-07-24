package servlets;

import edu.emory.mathcs.backport.java.util.Arrays;
import entities.Address;
import entities.MusicType;
import entities.Role;
import entities.User;
import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class UsersController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class UsersController extends HttpServlet {

    private UserRepo users;

    public UsersController() {
        this.users = new UserRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.getAllUsersWithEntities());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        boolean flag = false;
        User user = users.getUserDAO().get(req.getParameter("login"));
        if (user != null) {
            req.setAttribute("error", "Please, choose another login");
            doGet(req, resp);
            flag = true;
        }
        if (!flag) {
            List<String> list = new ArrayList<>(Arrays.asList(req.getParameterValues("interest")));
            List<MusicType> musicTypes = new ArrayList<>();
            for (String s : list) {
                MusicType musicType = MusicType.builder().name(s).build();
                musicTypes.add(musicType);
            }
            users.createUserWithEntities(
                    User.builder()
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
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
