<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="fr.utbm.jee_courses_management.controller.CourseController" %>
<%@ page import="fr.utbm.jee_courses_management.entity.Course"%>
<%@ page import="fr.utbm.jee_courses_management.entity.CourseSession"%>
<%@ page import="fr.utbm.jee_courses_management.util.Filter"%>
<%@ page import="java.time.LocalDate" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    CourseController controller = new CourseController();
    session.setAttribute("courses", controller.getCourses(new Filter(null, LocalDate.now(), null, null)));
    System.out.println(session.getAttribute("courses"));
%>

<!DOCTYPE html>
<html>
<head>
    <title>Courses Management - list</title>
</head>
<body>
<h1>Courses Management - Sessions list</h1>

<ul>
<c:forEach var="course" items="${sessionScope.courses}">
    <li>${course.title}:<br/>
        <ol>
            <c:forEach var="session" items="${course.sessions}">
                <li>Session at ${session.location.city} which begins the ${session.startingDate} and will finish the ${session.endingDate}</li>
            </c:forEach>
        </ol>
    </li>
</c:forEach>
</ul>

</body>
</html>