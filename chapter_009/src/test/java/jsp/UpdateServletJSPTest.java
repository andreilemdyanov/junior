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
 * Class UpdateServletJSPTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.05.2018
 */
public class UpdateServletJSPTest {
    @Test
    public void whenUpdateUserThenValuesChange() throws ServletException, IOException {
        UpdateServletJSP updateServlet = new UpdateServletJSP();
        updateServlet.getUsers().dropTables();
        updateServlet.getUsers().createTable();
        updateServlet.getUsers().createRoles();
        updateServlet.getUsers().createUser("root", "root", "root", "root", 2, "root", "root");
        updateServlet.getUsers().createUser("admin", "admin", "admin", "admin", 1, "admin", "admin");
        updateServlet.getUsers().createUser("some", "some", "some", "some", 2, "some", "some");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("id")).thenReturn("3");
        when(request.getParameter("login")).thenReturn("Another");
        when(request.getParameter("name")).thenReturn("UserUp");
        when(request.getParameter("password")).thenReturn("123");
        when(request.getParameter("email")).thenReturn("@boo");
        when(request.getParameter("country")).thenReturn("Russia");
        when(request.getParameter("city")).thenReturn("Moscow");
        when(request.getSession()).thenReturn(session);

        updateServlet.doPost(request, response);

        List<User> users = UserStore.INSTANCE.getAllUsers();

        assertThat(users.get(1).getLogin(), is("Another"));
        assertThat(users.get(1).getName(), is("UserUp"));
        assertThat(users.get(1).getPassword(), is("123"));
        assertThat(users.get(1).getEmail(), is("@boo"));
        assertThat(users.get(1).getCountry(), is("Russia"));
        assertThat(users.get(1).getCity(), is("Moscow"));

    }
}