function createDropReceiver(id) {
    var receiver = $('#' + id);
    receiver.on('dragover', function(evt) {
        return cancelDropReceiver(evt);
    });
    receiver.on('dragenter', function(evt) {
        return cancelDropReceiver(evt);
    });
    receiver.on('drop', function(evt) {
        evt.preventDefault();       // Avoid redirecting page to dropped resource
        window.dropEvent = evt;
        var originalEvent = evt.originalEvent;
        var dataTransfer = originalEvent.dataTransfer;
        var types = dataTransfer.types;
        if (types == null) {
            types = [];
        }
        var files = dataTransfer.files;
        var receiveTypes = receiver.data('receive-types');
        var data = [];
        for (var i = 0; i < types.length; i++) {
            var type = types[i];
            var acceptable = receiveTypes.indexOf(type) > -1;
            if (acceptable) {
                var s = dataTransfer.getData(type);
                s = decodeURI(encodeURI(s).replace(/%00/g, ''));
                data.push({
                    mimeType: type,
                    data: s
                })
            }
        }
        realtime.send({
            type: 'dropReceived',
            id: id,
            types: types,
            data: data
        });
        // TODO: add support for files
    });
}

function cancelDropReceiver(evt) {      // Avoid default implementation of drag functionality
    if (evt.preventDefault) {
        evt.preventDefault();
    }
    return false;
}