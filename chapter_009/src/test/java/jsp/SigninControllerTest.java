package jsp;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Class SigninControllerTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.05.2018
 */
public class SigninControllerTest {
    @Test
    public void whenSigninThenSessionSetAttributes() throws ServletException, IOException {
        SigninController signinController = new SigninController();
        signinController.getUsers().dropTables();
        signinController.getUsers().createTable();
        signinController.getUsers().createRoles();
        signinController.getUsers().createUser("admin", "admin", "admin", "admin", 1);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);


        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);

        signinController.doPost(request, response);

        verify(session, times(1)).setAttribute(anyObject(), eq("admin"));
        verify(session, times(1)).setAttribute(anyObject(), eq("ADMIN"));
    }

}