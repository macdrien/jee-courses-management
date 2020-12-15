<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="fr.utbm.jee_courses_management.controller.CourseController" %>
<%@ page import="fr.utbm.jee_courses_management.util.Filter"%>
<%@ page import="java.time.LocalDate" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    CourseController controller = new CourseController();
    session.setAttribute("courses", controller.getCourses(new Filter(null, LocalDate.now(), null, null)));
%>

<!DOCTYPE html>
<html>
<head>
    <title>Courses Management - list</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/list_script.js" type="text/javascript"></script>
</head>
<body>
<h1>Courses Management - Sessions list</h1>

<form id="filtering">
    <table><tr>
        <td><input id="courseTitle" type="text" placeholder="Course title"/></td>
        <td><input id="city" type="text" placeholder="City"/></td>
        <td><input id="startingDate" type="date" placeholder="starting date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/></td>
        <td><input id="endingDate" type="date" placeholder="ending date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/></td>
        <td><input id="btnFilter" type="button" value="Filter"></td>
    </tr></table>
</form>

<table>
    <thead>
        <td>Course</td>
        <td>City</td>
        <td>Starting date</td>
        <td>Ending date</td>
        <td>Students limitation</td>
    </thead>
    <tbody id="coursesList">
        <c:forEach var="course" items="${sessionScope.courses}">
            <c:forEach var="courseSession" items="${course.sessions}">
                <tr id="courseSession-${courseSession.id}">
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
                    <td><input id="btnRegister" type="button" value="Register"/></td>
                </tr>
            </c:forEach>
        </c:forEach>
    </tbody>
</table>

</body>
</html>