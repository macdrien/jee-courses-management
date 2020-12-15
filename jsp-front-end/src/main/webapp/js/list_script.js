$(document).ready(function () {
    $('#btnFilter').click(function(event) {
        event.preventDefault();

        const course = $('#courseTitle').val();
        const city = $('#city').val();
        const startingDate = $('#startingDate').val();
        const endingDate = $('#endingDate').val();

        var toSend = {};
        if (course != null && course.length !== 0) {
            toSend["course"] = course;
        }
        if (city != null && city.length !== 0) {
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
                        '<td>' + (session.maxStudents != null ? session.maxStudents : 'No limit') + '</td>' +
                    '</tr>');
                });
            });
        });
    });
});

$(document).ready(function() {
    $("#btnRegister").click(function(event) {
        event.preventDefault();

        // courseSessionId follows the pattern "courseSession-{id}"
        const courseSessionId = $("#btnRegister").parent().parent().attr("id");
        const id = courseSessionId.split("-")[1];

        window.location.href = "http://localhost:8080/courses-management/inscription.jsp?sessionId=" + id;
    })
})