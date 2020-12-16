$(document).ready(function () {
    $('#btnInscription').click(function(event) {
        const firstname = $("#firstname").val();
        const lastname = $("#lastname").val();
        const address = $("#address").val();
        const phone = $("#phone").val();
        const sessionId = $("#sessionId").text();

        if (firstname === null || firstname === "" ||
            lastname === null || lastname === "" ||
            address === null || address === "" ||
            phone === null || phone === "") {
            $("#informationMessage").append("Error : A required information is missing.<br/>" +
                "The required fields are: firstname, lastname, address and phone");
            return;
        }

        let clientToRegister = {
            firstname: firstname,
            lastname: lastname,
            address: address,
            phone: phone,
            sessionId: sessionId
        };

        const email = $("#email").val();
        if (email !== null && email !== "") {
            clientToRegister["email"] = email;
        }

        $.get('RegisterClientServlet', clientToRegister, function() {
        });
    });
});