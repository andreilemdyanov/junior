package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class DeleteServlet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.04.2018
 */
public class DeleteServlet extends HttpServlet {

    private UserStore users;

    @Override
    public void init() throws ServletException {
        users = UserStore.INSTANCE;
        users.createTable();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        users.deleteUser(req.getParameter("loginForDelete"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/list'>"
                + "Delete Complete"
                + "<center>"
                + "<input type='submit'>"
                + "</center>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();

    }
}
