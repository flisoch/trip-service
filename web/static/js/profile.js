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
                "bio": $("#info").val(),
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
const sendUserComment = (userId) => {
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
                `<div class="card border-secondary mb-3" id="comment_${data.id}">
                    <div class="card-header">
                        <button onclick='deleteUserComment(${userId},${data.id})' 
                            class="btn-sm btn btn-danger float-right">&times;</button>
                        <img src="${data.commentator.photo}" width="50">
                        <a href="/profile/${data.commentator.id}">${data.commentator.username}</a>
                    </div>
                    <div class="card-body text-secondary">
                        <h5 class="card-title">${data.text}</h5>
                        <p class="card-text">
                            ${data.date}
                        </p
                    </div>
                </div>`
            );
            message.remove();
        },

    });
    $("#comment-text").val('');
};

const deleteUserComment = (userId, commentId) => {
    let comment = $(`#comment_${commentId}`);

    $.ajax({
        url: `/profile/${userId}/comments/${commentId}`,
        type: 'POST',
        success: (data) => {
            comment.remove();
            let list = $(`#comments-container`);
            if(list[0].childElementCount == 0){
                list.append(`
                    <div id="no_comments_card">
                        <div class="card mb-3" id="no_requests_from_me_card">
                            <div class="card-body text-secondary">
                                <p class="card-text">
                                    No comments yet
                                </p>
                            </div>
                        </div>
                    </div>
                    
                `);
            }
        },
        error: (data) => {
            alert("error");
        }

    });
};
