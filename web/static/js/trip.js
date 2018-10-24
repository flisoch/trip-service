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
                        <a href="/profile/${tripId}">${data.commentator.username}</a>
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