package jsp;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class UpdateRoleServletTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.05.2018
 */
public class UpdateRoleServletTest {
    @Test
    public void whenUpdateRoleThenNameRoleChange() throws ServletException, IOException {
        UpdateRoleServlet updateRoleServlet = new UpdateRoleServlet();
        updateRoleServlet.getUsers().dropTables();
        updateRoleServlet.getUsers().createTable();
        updateRoleServlet.getUsers().createRoles();
        updateRoleServlet.getUsers().createUser("root", "root", "root", "root", 2);
        updateRoleServlet.getUsers().createUser("admin", "admin", "admin", "admin", 1);
        updateRoleServlet.getUsers().createUser("some", "some", "some", "some", 2);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("select")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("ADMIN");
        when(request.getParameter("id")).thenReturn("3");

        updateRoleServlet.doPost(request, response);

        List<User> users = UserStore.INSTANCE.getAllUsers();

        assertThat(users.get(1).getRole().getName(), is("ADMIN"));
    }
}