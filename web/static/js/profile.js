const enable = () => {
    $('.disable').prop('disabled', false);
}
const disable = () => {
    $('.disable').prop('disabled', true);
}
// $('.disable').prop('disabled', true);

function f() {
    $.ajax({
        url: "/trips",
        type:"POST",
        data: {
            "username": "vasya",
        },
        success: function (msg) {
            /*var lst = $("#found-list");
            lst.html("");
            for (var i = 0; i < msg.students.length; i++) {
                lst.append("<li>" + msg.students[i] + "</li>")
            }*/

        },
        error: function (msg) {
            alert(2);
        }
    });
}
function submitChanges() {

    $.ajax({
        url: "/profile/edit",
        type:'POST',
        data: {
                "username": $("#username").val(),
                "password": $("#password").val(),
                "name": $("#firstname").val(),
                "middlename": $("#middlename").val(),
                "lastname": $("#lastname").val(),
                "job": $("#working-place").val(),
                "address": $("#inputAddress").val(),
                "bio": $("#bio").val(),
                "age": $("#age").val()
        },
        success: function (msg) {
            /*var lst = $("#found-list");
            lst.html("");
            for (var i = 0; i < msg.students.length; i++) {
                lst.append("<li>" + msg.students[i] + "</li>")
            }*/
            alert($("#username").val());
        },
        error: function (msg) {
            alert(2);
        }
    });
    disable();
}