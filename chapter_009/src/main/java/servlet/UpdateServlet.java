package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class UpdateServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.04.2018
 */
public class UpdateServlet extends HttpServlet {
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
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/edit' method='POST'>"
                + "New Name:"
                + "<input type='text' size='40' name='name'><br>"
                + "New Login:"
                + "<input type='text' size='40' name='login'><br>"
                + "New Email:"
                + "<input type='text' size='40' name='email'><br>"
                + "<center>"
                + "<button type='submit' name='loginForUp' value='" + req.getParameter("loginForUp") + "'>Submit</button>"
                + "</center>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.updateUser(req.getParameter("loginForUp"), req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/list'>"
                + "Update Complete"
                + "<center>"
                + "<input type='submit'>"
                + "</center>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }
}
