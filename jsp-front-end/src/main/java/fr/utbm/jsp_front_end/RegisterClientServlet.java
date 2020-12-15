package fr.utbm.jsp_front_end;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utbm.jee_courses_management.controller.ClientController;
import fr.utbm.jee_courses_management.controller.CourseSessionController;
import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.entity.CourseSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterClientServlet", urlPatterns = {"/RegisterClientServlet"})
public class RegisterClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client toRegister = new Client();
        toRegister.setFirstname(req.getParameter("firstname"));
        toRegister.setLastname(req.getParameter("lastname"));
        toRegister.setAdress(req.getParameter("address"));
        toRegister.setPhoneNumber(req.getParameter("phone"));
        toRegister.setEmail(req.getParameter("email"));

        CourseSession session = new CourseSession();
        session.setId(Integer.parseInt(req.getParameter("sessionId")));
        toRegister.setSession(session);

        // The CourseSession does not exist
        if (new CourseSessionController().getCourseSessionById(session.getId()) == null) {
            resp.setStatus(404);
            return;
        }

        ClientController controller = new ClientController();
        Client client = controller.registerClient(toRegister);

        if (client != null) {
            // Registration complete. Return the client as a JSON
            resp.setStatus(201);
            resp.setContentType("application/json");
            resp.getWriter().println(new ObjectMapper().writeValueAsString(client));
        }
        else
            // Error during the registration
            resp.setStatus(500);
    }
}
