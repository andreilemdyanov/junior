package jsp;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Class JSONController.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 08.07.2018
 */
public class JSONController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        Map<String, String> map = new HashMap<>();
        List<String> russia = new ArrayList<>(Arrays.asList("Moscow", "Piter", "Ekaterinburg"));
        List<String> usa = new ArrayList<>(Arrays.asList("Detroit", "New-York", "Vashington"));
        ObjectMapper mapper = new ObjectMapper();
        map.put("Russia", mapper.writeValueAsString(russia));
        map.put("USA", mapper.writeValueAsString(usa));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.print(map.get(req.getParameter("country")));
        writer.flush();
    }

}
