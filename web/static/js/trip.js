const apply = (tripId) => {
    $.ajax({
        url: `/trips/${tripId}/requests`,
        type: 'POST',
        success: (data) => {
            let tripStatusElement = $(`#trip-${tripId}-status`);
            tripStatusElement.empty();
            tripStatusElement.append(`
                <div>Status: Request sent</div>
                    <div class="btn btn-xs btn-danger" onclick="cancelRequest(${tripId});event.stopPropagation();">
                        Cancel request
                    </div>
            `)
        }
    });
};

const cancelRequest = (tripId) => {
    $.ajax({
        url: `/trips/${tripId}`,
        type: 'PUT',
        contentType: "application/json",
        data: JSON.stringify({action: "cancelRequest"}),
        success: (data) => {
            let tripStatusElement = $(`#trip-${tripId}-status`);
            tripStatusElement.empty();
            tripStatusElement.append(`
             <div class="btn btn-xs btn-primary" onclick="apply(${tripId});event.stopPropagation();">
                Apply
             </div>
            `)
        }
    });
};
const removeUserFromTrip = (tripId, userId) => {
    $.ajax({
        url: `/trips/${tripId}`,
        type: 'PUT',
        contentType: "application/json",
        data: JSON.stringify({action: "kickUser", userId: userId}),
        success: (data) => {
            let passangerToRemove = $(`#passanger-${userId}`);
            passangerToRemove.remove();
            let passangersContainer = $(`#passangers-container`);
            if(passangersContainer[0].childElementCount == 1){
                passangersContainer.append(`
                <div>No passangers yet</div>
                `)
            }

        }
    });
};

const leaveTrip = (tripId) => {
    $.ajax({
        url: `/trips/${tripId}`,
        type: 'PUT',
        contentType: "application/json",
        data: JSON.stringify({action: "leaveTrip"}),
        success: (data) => {
            let tripStatusElement = $(`#trip-${tripId}-status`);
            tripStatusElement.empty();
            tripStatusElement.append(`
             <div class="btn btn-xs btn-primary" onclick="apply(${tripId});event.stopPropagation();">
                Apply
             </div>
            `)
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
            let message = $('#no_comments_card');
            list.append(
                `<div class="card border-secondary mb-3" id="comment_${data.id}">
                    <div class="card-header">
                        <button onclick='deleteTripComment(${tripId},${data.id})' 
                        class="btn-sm btn btn-danger float-right">&times;</button>
                                            
                        <img src="${data.commentator.photo}" width="50">
                        <a href="/profile/${data.commentator.id}">${data.commentator.username}</a>
                    </div>
                    <div class="card-body text-secondary">
                        <h5 class="card-title">${data.text}</h5>
                        <p class="card-text">
                            ${data.dateTime}
                        </p
                    </div>
                </div>`
            );
            message.remove();
        },

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
    let jsonString = JSON.stringify({
        info: info,
        departure: departure,
        destination: destination,
        seats: seats,
        date: date
    });

    $.ajax({
        url: `/trips/${id}`,
        type: "PUT",
        contentType: "application/json",
        data: jsonString,
        success: function (msg) {
            disable();
        },
        error: function (msg) {
        }
    });
}

const deleteTrip = (tripId) => {
    let trip = $(`#trip_${tripId}`);

    $.ajax({
        url: `/trips/${tripId}`,
        type: 'DELETE',
        success: (data) => {
            trip.remove();
        },
        error: (data) => {
            alert("error");
        }

    });
};

const deleteTripComment = (tripId, commentId) => {
    let comment = $(`#comment_${commentId}`);
    $.ajax({
        url: `/trips/${tripId}/comments/${commentId}`,
        type: 'POST',
        success: (data) => {
            comment.remove();
            let list = $(`#comments-container`);
            if (list[0].childElementCount == 0) {
                list.append(`
                    <div class="card mb-3" id="no_requests_from_me_card">

                        <div class="card-body text-secondary">
                            <p class="card-text">
                                No comments yet
                            </p>
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
