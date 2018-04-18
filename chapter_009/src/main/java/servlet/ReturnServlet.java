package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class ReturnServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.04.2018
 */
public class ReturnServlet extends HttpServlet {
    private UserStore users;

    @Override
    public void init() throws ServletException {
        users = UserStore.INSTANCE;
        users.createTable();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> list = users.getAllUsers();
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "<table border='1'>");
        for (User u : list) {
            sb.append("<tr><td>" + u.getName()
                    + "</td><td>" + u.getLogin()
                    + "</td><td>" + u.getEmail()
                    + "</td><td>" + u.getCreateDate().getTime()
                    + "</td><td>"
                    + "<form action='" + req.getContextPath() + "/edit'>"
                    + "<button value='" + u.getLogin() + "' name='loginForUp'>Update</button>"
                    + "</form>"
                    + "</td><td>"
                    + "<form action='" + req.getContextPath() + "/delete' method='POST'>"
                    + "<button value='" + u.getLogin() + "' name='loginForDelete'>Delete</button>"
                    + "</form>"
                    + "</td></tr>");
        }
        writer.append(sb.toString() + "</table>");
        writer.append("<form action='" + req.getContextPath() + "/create'>"
                + "<center>"
                + "<button type='submit' name='create'>Create User</button>"
                + "</center>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();

    }
}
