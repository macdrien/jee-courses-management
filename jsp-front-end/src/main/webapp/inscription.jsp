<%@ page import="fr.utbm.jee_courses_management.controller.CourseSessionController" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String sessionId = request.getParameter("sessionId");
    if (sessionId != null && !sessionId.isBlank())
        session.setAttribute("courseSession",
                new CourseSessionController().getCourseSessionById(Integer.parseInt(sessionId)));
%>

<!DOCTYPE html>
<html>
<head>
    <title>Courses Management - Inscription</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/inscription_script.js" type="text/javascript"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css"/>
</head>

<body>
<h1>Courses Management</h1>
<h2>Inscription for the course ${courseSession.course.title}</h2>
<h4>Session at ${courseSession.location.city} from ${courseSession.startingDate} to ${courseSession.endingDate}.</h4>

<p id="informationMessage"></p>

<form>
    <table class="table table-borderless">
        <thead>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Address</th>
            <th>Phone number</th>
            <th>Mail address</th>
        </thead>
        <tr>
            <td><input id="firstname" placeholder="Firstname" type="text" required/></td>
            <td><input id="lastname" placeholder="Lastname" type="text" required/></td>
            <td><input id="address" placeholder="Address" type="text" required/></td>
            <td><input id="phone" placeholder="0000000000" type="text" required/></td>
            <td><input id="email" placeholder="Mail address" type="text"/></td>
            <td><button id="btnInscription" class="btn btn-success" onclick="registerClient()">Register</button></td>
        </tr>
    </table>
</form>

<p class="back-message">Not the right session? Come back to the session list by <a href="list.jsp">clicking here</a></p>

<span hidden id="sessionId">${courseSession.id}</span>
</body>