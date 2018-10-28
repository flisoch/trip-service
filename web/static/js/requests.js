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