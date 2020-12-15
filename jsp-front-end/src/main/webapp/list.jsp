<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<h1>Courses Management - Sessions list</h1>

<div class="filters">
    <form id="filtering">
        <table class="table table-borderless">
            <thead>
                <th>Course title</th>
                <th>City</th>
                <th>Starting date</th>
                <th>Ending date</th>
            </thead>
            <tr>
                <td><input id="courseTitle" class="form-control" type="text" placeholder="Course title"/></td>
                <td><input id="city" class="form-control" type="text" placeholder="City"/></td>
                <td><input id="startingDate" class="form-control" type="date" placeholder="starting date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/></td>
                <td><input id="endingDate" class="form-control" type="date" placeholder="ending date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/></td>
                <td><button id="btnFilter" class="btn btn-secondary">Filter</button></td>
            </tr>
        </table>
    </form>
</div>

<div class="sessions-list">
    <table class="table table-dark table-striped">
        <thead>
            <th scope="col">Course</th>
            <th scope="col">City</th>
            <th scope="col">Starting date</th>
            <th scope="col">Ending date</th>
            <th scope="col">Students limitation</th>
            <th scope="col">Occupancy rate</th>
            <th scope="col" class="button-column"></th>
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
                                <fmt:parseNumber var="rate" integerOnly="true" type="number"
                                                 value="${courseSession.clientNumber * 100 / courseSession.maxStudents}" />
                                <td>${rate}%</td>
                            </c:when>
                            <c:otherwise>
                                <td>No limit</td>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                        <td class="button-column"><button id="btnRegister-${courseSession.id}" class="btn btn-outline-light" onclick="register(${courseSession.id})">Register</button></td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>