<%@ page import="fr.utbm.jee_courses_management.controller.CourseSessionController" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    session.setAttribute("courseSession",
            new CourseSessionController().getCourseSessionById(Integer.parseInt(request.getParameter("sessionId"))));
%>

<!DOCTYPE html>
<html>
<head>
    <title>Courses Management - Inscription</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/list_script.js" type="text/javascript"></script>
</head>

<body>
<h1>Courses Management - Inscription for the course ${courseSession.course.title}</h1>

<p>Session at ${courseSession.location.city} from ${courseSession.startingDate} to ${courseSession.endingDate}.</p>

<form>
    <table>
        <tr>
            <td><label>Firstname</label></td>
            <td><label>Lastname</label></td>
            <td><label>Address</label></td>
            <td><label>Phone number</label></td>
            <td><label>Mail address</label></td>
        </tr>
        <tr>
            <td><input id="firstname" placeholder="Firstname" type="text" required/></td>
            <td><input id="lastname" placeholder="Lastname" type="text" required/></td>
            <td><input id="address" placeholder="Address" type="text" required/></td>
            <td><input id="phone" placeholder="00.00.00.00.00" type="text" required/></td>
            <td><input id="email" placeholder="Mail address" type="text" required/></td>
            <td><input id="btnInscription" type="button" value="Register"/></td>
        </tr>
    </table>
</form>

<p>Not the right session? Come back to the session list by <a href="list.jsp">clicking here</a></p>
</body>