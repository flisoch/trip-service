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
    //todo: get from data comment info
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
                    <div class="card-header">NIna</div>
                    <div class="card-body text-secondary">
                        <h5 class="card-title">Bolshe ya s etim ne poedu...</h5>
                        <p class="card-text">
                            ${text}
                        </p>
                    </div>
                </div>`
            );
        }

    });
    $("#comment-text").val('');
    $('html, body').scrollTop($(document).height());


};