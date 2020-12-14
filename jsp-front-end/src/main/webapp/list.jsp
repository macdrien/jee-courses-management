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

<table>
    <thead>
        <td>Course</td>
        <td>City</td>
        <td>Starting date</td>
        <td>Ending date</td>
        <td>Students limitation</td>
    </thead>
    <c:forEach var="course" items="${sessionScope.courses}">
        <c:forEach var="courseSession" items="${course.sessions}">

            <tr>
                <td>${course.title}</td>
                <td>${courseSession.location.city}</td>
                <td>${courseSession.startingDate}</td>
                <td>${courseSession.endingDate}</td>
                <c:choose>
                    <c:when test="${courseSession.maxStudents != null && courseSession.maxStudents > 0}">
                        <td>${courseSession.maxStudents}</td>
                    </c:when>
                    <c:otherwise>
                        <td>No limit</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </c:forEach>
</table>

</body>
</html>