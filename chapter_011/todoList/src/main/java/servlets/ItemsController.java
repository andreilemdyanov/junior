package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ItemDao;
import models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ItemsController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.08.2018
 */
public class ItemsController extends HttpServlet {

    private ItemDao dao;

    public ItemsController() {
        this.dao = ItemDao.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        List<String> items = new ArrayList<>();
        for (Item item : dao.getAllItems()) {
            items.add(mapper.writeValueAsString(item));
        }
        PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(resp.getOutputStream(), "UTF-8"), true);
        writer.print(items);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao.addItem(req.getParameter("description"));
    }
}
