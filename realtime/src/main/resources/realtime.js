// Avoid `console` errors in browsers that lack a console.
if (!(window.console && console.log)) {
    (function() {
        var noop = function() {};
        var methods = ['assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error', 'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'markTimeline', 'table', 'time', 'timeEnd', 'timeStamp', 'trace', 'warn'];
        var length = methods.length;
        var console = window.console = {};
        while (length--) {
            console[methods[length]] = noop;
        }
    }());
}

var host = document.location.host;

var communicator = new Communicator();
// TODO: handle customization
communicator.webSocketURL = 'ws://' + host + '/websocket';
communicator.pollingURL = 'http://' + host + '/ajax/polling';
communicator.sendingURL = 'http://' + host + '/ajax/receiver';
communicator.on('eval', jsEval);

function connectRealtime(id, debug) {
    log('Establishing realtime connection...');
    communicator.debug = debug;
    communicator.id = id;
    communicator.connect();
}

function svgEventHandler(evt) {
    var element = $(evt.currentTarget);
    var id = element.attr('id');
    if (id == null) {       // TODO: Should we always be using target instead of currentTarget?
        element = $(evt.target);
        id = element.attr('id');
    }
    if (evt instanceof MouseEvent) {
        communicator.send('svgMouseEvent', id, {
            event: evt.type,
            altKey: evt.altKey,
            button: evt.button,
            clientX: evt.clientX,
            clientY: evt.clientY,
            ctrlKey: evt.ctrlKey,
            metaKey: evt.metaKey,
            screenX: evt.screenX,
            screenY: evt.screenY,
            shiftKey: evt.shiftKey
        });
    } else if (evt instanceof MutationEvent) {
        communicator.send('svgMutationEvent', id, {
            event: evt.type,
            attrChange: evt.attrChange,
            attrName: evt.attrName,
            newValue: evt.newValue,
            prevValue: evt.prevValue
        });
    } else if (evt instanceof UIEvent) {
        communicator.send('svgUIEvent', id, {
            event: evt.type,
            detail: evt.detail
        });
    } else {
        communicator.send('svgEvent', id, {
            event: evt.type
        });
    }
}

function jsFireChange(element) {
    var id = element.attr('id');
    communicator.send('change', id, {
        value: element.val()
    });
}

function jsFireGenericEvent(element, eventType) {
    jsFire(element, 'event', {
        event: eventType
    });
}

function jsFire(element, event, message) {
    var id = element.attr('id');
    communicator.send(event, id, message)
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
            jsFireChange(element);
//            log('sending change event: ' + id + ' - ' + element.val());
        }
        // TODO: support mouse events better
        if (e.type == 'keydown' || e.type == 'keypress' || e.type == 'keyup') {
            communicator.send('keyEvent', id, {
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
            jsFireGenericEvent(element, e.type);
        }
    } else {
        var target = $(e.target);
        log('jsEventHandler - No id found for ' + element + ', type: ' + e.type + ', targetId: ' + target.attr('id'));
    }
}

var logEvals = false;

function jsEval(message) {
    var json = jQuery.parseJSON(message);
    var content = json['content'];
    var instruction = json['instruction'];
    if (logEvals) {
        log('Instruction: ' + instruction + ', Content: ' + content);
    }
    eval(instruction);
}

function invokeForId(id, f) {
    if ($('#' + id).length == 0) {
        setTimeout(function() {
            invokeForId(id, f);
        }, 10);
    } else {
        f();
    }
}

var logHistory = [];

function log(msg) {
    var message = new Date().toLocaleString() + ': ' + msg;
    logHistory.push(message);
    console.log(message);
}

$(document).ready(function() {
    var ctrlDown = false;
    var ctrlKey = 17;
    var keyCodeL = 76;
    var keyCodeI = 73;

    $(document).keydown(function(e) {
        if (e.keyCode == ctrlKey) {
            ctrlDown = true;
        }
    });
    $(document).keyup(function(e) {
        if (e.keyCode == ctrlKey) {
            ctrlDown = false;
        }
    });
    $(document).keydown(function(e) {
        if (ctrlDown && e.keyCode == keyCodeL) {
            var s = 'Log Entries: ' + logHistory.length + '\n';
            for (var i = 0; i < logHistory.length; i++) {
                s += logHistory[i] + '\n';
            }
            alert(s);
        } else if (ctrlDown && e.keyCode == keyCodeI) {     // Toggle logging evals
            logEvals = !logEvals;
            console.log('Setting logging of evals to: ' + logEvals);
        }
    });
});