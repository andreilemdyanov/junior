package jsp;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class DeleteServletJSPTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.05.2018
 */
public class DeleteServletJSPTest {
    @Test
    public void whenDeleteUserThenUsersIsEmpty() throws ServletException, IOException {
        DeleteServletJSP deleteServlet = new DeleteServletJSP();
        deleteServlet.getUsers().dropTables();
        deleteServlet.getUsers().createTable();
        deleteServlet.getUsers().createRoles();
        deleteServlet.getUsers().createUser("root", "root", "root", "root", 2, "root", "root");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        deleteServlet.doPost(request, response);

        List<User> users = UserStore.INSTANCE.getAllUsers();

        assertTrue(users.isEmpty());

    }
}