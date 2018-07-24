package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Role;
import repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class JsonRoleController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class JsonRoleController extends HttpServlet {

    private UserRepo users;

    public JsonRoleController() {
        this.users = new UserRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<Role> all = users.getRoleDAO().getAll();
        List<String> list = new ArrayList<>();
        for (Role role : all) {
            list.add(role.getName());
        }
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.print(mapper.writeValueAsString(list));
        writer.flush();
    }
}
