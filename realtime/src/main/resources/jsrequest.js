function jsRequest(id, requests) {
    var response = [];
    for (var index = 0; index < requests.length; index++) {
        var request = requests[index];
        response[index] = request();
    }
    realtimeSend(null, 'jsresponse', {
        responseId: id,
        responses: response
    });
}