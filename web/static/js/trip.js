const apply = (tripId) => {
    $.ajax({
        url: `/trips/${tripId}/apply`,
        type: 'POST',
        success: (data) => {
            alert('aplly send!');
        }
    });
};

const sendComment = (tripId) => {
    let text = $("#comment-text").val();

    $.ajax({
        url: `/trips/${tripId}/comments`,
        data: {
            "text": text,
        },
        type: 'POST',
        success: (data) => {
            let list = $('#comments-container');
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
        }

    });
    $("#comment-text").val('');
    // $('html, body').scrollTop($(document).height());
};


function submitTripChanges(id) {
    let info = $("#info").val();
    let departure = $("#departure").val();
    let destination = $("#destination").val();
    let seats = $("#seats").val();
    let date = $("#timeToInputField").val();

    $.ajax({
        url: `/trips/${id}/edit`,
        type:"POST",
        data: {
            "info": info,
            "departure": departure,
            "destination": destination,
            "seats":seats,
            "date":date,
        },
        success: function (msg) {
            alert("successfully updated");
        },
        error: function (msg) {
            alert(2);
        }
    });
}