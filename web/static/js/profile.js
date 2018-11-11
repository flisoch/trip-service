const enable = () => {
    $('.disable').prop('disabled', false);
}
const disable = () => {
    $('.disable').prop('disabled', true);
}
// $('.disable').prop('disabled', true);


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
const sendComment = (userId) => {
    let text = $("#comment-text").val();

    $.ajax({
        url: `/profile/${userId}/comments`,
        data: {
            "text": text,
        },
        type: 'POST',
        success: (data) => {
            let list = $('#comments-container');
            let message = $('#no_comments_card');
            list.append(
                `<div class="card border-secondary mb-3">
                    <div class="card-header">
                        <a href="/profile/${data.commentator.id}">${data.commentator.username}</a>
                    </div>
                    <div class="card-body text-secondary">
                        <h5 class="card-title">${data.text}</h5>
                        <p class="card-text">
                            ${new Date(data.date).toString()}
                        </p
                    </div>
                </div>`
            );
            message.remove();
        }

    });
    $("#comment-text").val('');
};