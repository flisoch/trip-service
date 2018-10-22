const apply = (tripId) => {
    $.ajax({
        url: `/trips/${tripId}/apply`,
        type: 'POST',
        success: (data) => {
            alert('Вы записались!');
        }
    });
}
