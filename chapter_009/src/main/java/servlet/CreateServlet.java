package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class CreateServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.04.2018
 */
public class CreateServlet extends HttpServlet {
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
                + "<form action='" + req.getContextPath() + "/create' method='POST'>"
                + "Create Name:"
                + "<input type='text' size='40' name='name'><br>"
                + "Create Login:"
                + "<input type='text' size='40' name='login'><br>"
                + "Create Email:"
                + "<input type='text' size='40' name='email'><br>"
                + "<center>"
                + "<input type='submit'>"
                + "</center>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.createTable();
        users.createUser(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "Creation complete"
                + "<form action='" + req.getContextPath() + "/list'>"
                + "<center>"
                + "<input type='submit'>"
                + "</center>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }
}
