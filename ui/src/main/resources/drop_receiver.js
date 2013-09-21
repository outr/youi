function createDropReceiver(id) {
    var receiver = $('#' + id);
    receiver.on('dragover', function(evt) {
        return cancelDropReceiver(evt);
    });
    receiver.on('dragenter', function(evt) {
        return cancelDropReceiver(evt);
    });
    receiver.on('drop', function(evt) {
        console.log('Dropped!');
        evt.preventDefault();       // Avoid redirecting page to dropped resource
        var originalEvent = evt.originalEvent;
        var dataTransfer = originalEvent.dataTransfer;
        var types = dataTransfer.types;
        var files = dataTransfer.files;
        console.log('Types: ' + JSON.stringify(types) + ' (' + types.length + '), Files: ' + JSON.stringify(files) + ' (' + files.length + ')');
        var receiveTypes = receiver.data('receive-types');
        var data = {};
        for (var i = 0; i < types.length; i++) {
            var type = types[i];
            var acceptable = receiveTypes.indexOf(type) > -1;
            if (acceptable) {
                data[type] = dataTransfer.getData(type);
            }
            console.log('  Type (' + acceptable + '): ' + type + ', Value: ' + dataTransfer.getData(type));
        }
        communicator.send('dropped', id, {
            types: types,
            data: data
        });
    });
}

function cancelDropReceiver(evt) {      // Avoid default implementation of drag functionality
    if (evt.preventDefault) {
        evt.preventDefault();
    }
    return false;
}