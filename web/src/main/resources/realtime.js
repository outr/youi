var host = document.location.host;

var communicator = new Communicator();
// TODO: handle customization
communicator.webSocketURL = 'ws://' + host + '/websocket';
communicator.pollingURL = 'http://' + host + '/ajax/polling';
communicator.sendingURL = 'http://' + host + '/ajax/receiver';
communicator.on('eval', jsEval);

function connectRealtime(id) {
    console.log('Establishing realtime connection...');
    communicator.debug = false;
    communicator.id = id;
    communicator.connect();
}

// TODO: add support in communicator for filtering out overlapping messages
function jsEventHandler(e, data, fireChange, onlyLast) {
    var element = $(e.currentTarget);
    var id = element.attr('id');
    if (id == null) {       // TODO: Should we always be using target instead of currentTarget?
        element = $(e.target);
        id = element.attr('id');
    }
    if (id != null) {
        if (e.type == 'change' || fireChange) {
            communicator.send('change', {
                id: id,
                value: element.val()
            });
//            console.log('sending change event: ' + id + ' - ' + element.val());
        }
        // TODO: support mouse events better
        if (e.type == 'keydown' || e.type == 'keypress' || e.type == 'keyup') {
            communicator.send('keyEvent', {
                id: id,
                event: e.type,
                altKey: e.altKey,
                char: e.charCode,
                ctrlKey: e.ctrlKey,
                key: e.keyCode,
                locale: e.locale,
                location: e.location,
                metaKey: e.metaKey,
                repeat: e.repeat,
                shiftKey: e.shiftKey
            });
        } else {
            communicator.send('event', {
                id: id,
                event: e.type
            });
        }
    } else {
        var target = $(e.target);
        console.log('jsEventHandler - No id found for ' + element + ', type: ' + e.type + ', targetId: ' + target.attr('id'));
    }
}

function jsEval(message) {
    var json = jQuery.parseJSON(message);
    var content = json['content'];
    var instruction = json['instruction'];
//    console.log('Instruction: ' + instruction + ', Content: ' + content);
    eval(instruction);
}