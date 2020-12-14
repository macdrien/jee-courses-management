package fr.utbm.jsp_front_end;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utbm.jee_courses_management.controller.CourseController;
import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.util.Filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "GetCourseSessions", urlPatterns = {"/GetCourseSessions"})
public class GetCourseSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseController controller = new CourseController();

        Filter filter = new Filter(
                req.getParameter("course"),
                (req.getParameter("startingDate") != null ? LocalDate.parse(req.getParameter("startingDate")) : null),
                (req.getParameter("endingDate") != null ? LocalDate.parse(req.getParameter("endingDate")) : null),
                req.getParameter("city"));

        List<Course> courses = controller.getCourses(filter);
        String jsonCourses = new ObjectMapper().writeValueAsString(courses);

        resp.setContentType("application/json");
        resp.getWriter().print(jsonCourses);
    }
}
