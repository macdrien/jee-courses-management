$(document).ready(function () {
    $('#btnFilter').click(function(event) {
        event.preventDefault();

        const course = $('#courseTitle').val();
        const city = $('#city').val();
        const startingDate = $('#startingDate').val();
        const endingDate = $('#endingDate').val();

        var toSend = {};
        if (course !== null && course.length !== 0 && course !== "Course") {
            toSend["course"] = course;
        }
        if (city != null && city.length !== 0 && course !== "City") {
            toSend["city"] = city;
        }
        if (startingDate != null && startingDate.length !== 0) {
            toSend["startingDate"] = startingDate;
        }
        if (endingDate != null && endingDate.length !== 0) {
            toSend["endingDate"] = endingDate;
        }

        $.get('GetCourseSessions', toSend, function(response) {
            $("#coursesList").children().remove();
            response.forEach(function(course) {
                course.sessions.forEach(function(session) {
                    const starts = session.startingDate.year + '-' +
                        (session.startingDate.monthValue >= 10 ? session.startingDate.monthValue : '0'+session.startingDate.monthValue) + '-' +
                        (session.startingDate.dayOfMonth >= 10 ? session.startingDate.dayOfMonth : '0'+session.startingDate.dayOfMonth);
                    const finish = session.endingDate.year + '-' +
                        (session.endingDate.monthValue >= 10 ? session.endingDate.monthValue : '0'+session.endingDate.monthValue) + '-' +
                        (session.endingDate.dayOfMonth >= 10 ? session.endingDate.dayOfMonth : '0'+session.endingDate.dayOfMonth);

                    $("#coursesList").append('<tr>' +
                        '<td>' + course.title + '</td>' +
                        '<td>' + session.location.city + '</td>' +
                        '<td>' + starts + '</td>' +
                        '<td>' + finish + '</td>' +
                        (session.maxStudents != null ?
                            '<td>' + session.maxStudents + '</td><td>' + (parseInt(session.clientNumber * 100 / session.maxStudents)) + '%</td>' :
                            '<td>No limit</td><td></td>'
                        ) +
                        '<td class="button-column">' +
                            '<button id="btnRegister-' + session.id + '" class="btn btn-outline-light" onClick="register(' + session.id + ')">' +
                                'Register' +
                            '</button>' +
                        '</td>' +
                    '</tr>');
                });
            });
        });
    });
});

function register(courseSessionId) {
    window.location.href = "http://localhost:8080/courses-management/inscription.jsp?sessionId=" + courseSessionId;
}