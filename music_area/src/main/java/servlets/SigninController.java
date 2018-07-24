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

/**
 * Class SigninController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class SigninController extends HttpServlet {

    private UserRepo repo;

    public SigninController() {
        this.repo = new UserRepo();
        repo.getUsers().dropTables();
        repo.getUsers().createTables();
        repo.getUsers().fillRoles();
        repo.getUsers().fillMusicTypes();
        repo.createUserWithEntities(
                User.builder()
                        .login("1")
                        .password("1")
                        .firstName("Pavel")
                        .lastName("Popov")
                        .timeCreate(Calendar.getInstance())
                        .build(),
                Role.builder()
                        .name("USER")
                        .build(),
                Address.builder()
                        .country("Russia")
                        .city("Moscow")
                        .street("Gagarina")
                        .build(),
                new ArrayList<>(Arrays.asList(MusicType.builder()
                        .name("RAP")
                        .build())));

        repo.createUserWithEntities(
                User.builder()
                        .login("2")
                        .password("2")
                        .firstName("Hill")
                        .lastName("Procs")
                        .timeCreate(Calendar.getInstance())
                        .build(),
                Role.builder()
                        .name("ADMIN")
                        .build(),
                Address.builder()
                        .country("USA")
                        .city("NY")
                        .street("Sweet")
                        .build(),
                new ArrayList<>(Arrays.asList(MusicType.builder()
                        .name("POP")
                        .build())));

        repo.createUserWithEntities(
                User.builder()
                        .login("3")
                        .password("3")
                        .firstName("Joe")
                        .lastName("Lee")
                        .timeCreate(Calendar.getInstance())
                        .build(),
                Role.builder()
                        .name("ADMIN")
                        .build(),
                Address.builder()
                        .country("Canada")
                        .city("Monreal")
                        .street("Pabs")
                        .build(),
                new ArrayList<>(Arrays.asList(MusicType.builder()
                        .name("POP")
                        .build(), MusicType.builder()
                        .name("JAZZ")
                        .build(), MusicType.builder()
                        .name("ROCK")
                        .build())));

        repo.createUserWithEntities(
                User.builder()
                        .login("4")
                        .password("4")
                        .firstName("Vasily")
                        .lastName("Ivanov")
                        .timeCreate(Calendar.getInstance())
                        .build(),
                Role.builder()
                        .name("MODERATOR")
                        .build(),
                Address.builder()
                        .country("Russia")
                        .city("Saint-P")
                        .street("Gromova")
                        .build(),
                new ArrayList<>(Arrays.asList(MusicType.builder()
                        .name("POP")
                        .build(), MusicType.builder()
                        .name("RAP")
                        .build())));


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (repo.getUsers().isCredentional(login, password) != 0) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            if (repo.getUsers().isCredentional(login, password) == 1) {
                session.setAttribute("role", "ADMIN");
            } else if (repo.getUsers().isCredentional(login, password) == 2) {
                session.setAttribute("role", "MODERATOR");
            } else {
                session.setAttribute("role", "USER");
            }

            resp.sendRedirect(String.format("%s", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentional invalid");
            doGet(req, resp);
        }
    }
}
