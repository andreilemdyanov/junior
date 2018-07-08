package jsp;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class UsersControllerTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.05.2018
 */
public class UsersControllerTest {
    @Test
    public void whenCreateUserThenUserAdd() throws ServletException, IOException {
        UsersController controller = new UsersController();
        controller.getUsers().dropTables();
        controller.getUsers().createTable();
        controller.getUsers().createRoles();
        controller.getUsers().createUser("root", "root", "root", "root", 2, "Russia", "Moscow");
        controller.getUsers().createUser("admin", "admin", "admin", "admin", 1, "USA", "Detroit");
        controller.getUsers().createUser("some", "some", "some", "some", 2, "Russia", "Piter");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("Andrei");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("name")).thenReturn("Bob");
        when(request.getParameter("email")).thenReturn("@mail");
        when(request.getParameter("role")).thenReturn("2");
        when(request.getParameter("country")).thenReturn("Russia");
        when(request.getParameter("city")).thenReturn("Moscow");
        controller.doPost(request, response);

        List<User> users = UserStore.INSTANCE.getAllUsers();
        assertThat(users.get(1).getLogin(), is("Andrei"));
    }
}