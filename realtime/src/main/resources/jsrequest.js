function jsRequest(id, requests) {
    var response = [];
    for (var index = 0; index < requests.length; index++) {
        var request = requests[index];
        response[index] = request();
    }
    communicator.send('jsresponse', null, {
        id: id,
        responses: response
    });
}