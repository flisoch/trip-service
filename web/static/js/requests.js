const accept = (tripId, userId, requestId) => {
    let requestCard = $(`#request_${requestId}`);

    $.ajax({
        url: `/requests/accept`,
        type: 'POST',
        data: {
            'trip_id': tripId,
            'user_id': userId,
        },

        success: (data) => {
            requestCard.remove();
        },
        error:(data) => {
            alert("error");
        }
    });
};

const reject = (tripId, userId, requestId) => {
    let requestCard = $(`#request_${requestId}`);
    $.ajax({
        url: `/requests/reject`,
        type: 'POST',
        data: {
            'trip_id': tripId,
            'user_id': userId,
        },
        success: (data) => {
            requestCard.remove();
        }
    });
};

const cancel = (requestId) => {
    let requestCard = $(`#request_${requestId}`);
    $.ajax({
        url: `/profile/requests`,
        type: 'POST',
        data: {
            'request_id': requestId,
        },
        success: (data) => {
            requestCard.remove();
            let list = $(`#requests-container`);
            if(!list.hasChildNodes()){
                list.append(`
                    <div class="card mb-3" id="no_requests_from_me_card">

                        <div class="card-body text-secondary">
                            <p class="card-text">
                                No requests yet
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