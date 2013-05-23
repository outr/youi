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

// Work-around for bad drag-and-drop support - forces a change event upon dropped text
$(document).on('drop', 'input, textarea', function() {
    var element = $(this);
    var currentValue = element.val();
    var times = 0;
    var poller = function() {
        if (element.val() == currentValue) {
            if (times < 10) {
                times += 1;
                setTimeout(poller, 10);
            } else {
                // Nothing changed, give up
            }
        } else {
            element.change();
        }
    };
    poller();
});

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
    var value = element.val();
    if (element.is('input') && (element.prop('type') == 'checkbox' || element.prop('type') == 'radio')) {
        value = element.prop('checked');
    } else if (element.is('form')) {        // Send form data via change event
        var formData = element.serializeForm();
        value = '';
        jQuery.each(formData, function(k, v) {
            if (value != '') {
                value += '&';
            }
            value += k;
            value += '=';
            value += v;
        });
    }
    communicator.send('change', id, {
        value: value
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
        if (id == null) {
            element = $(e.srcElement);
            id = element.attr('id');
        }
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
    try {
        eval(instruction);
    } catch(err) {
        log('Error occurred (' + err.message + ') while attempting to evaluate instruction: [' + instruction + '].')
    }
}

function parseSVG(content) {
    var parser = new DOMParser();
    parser.async = false;
    content = content.toString().trim();
    content = '<svg xmlns=\'http://www.w3.org/2000/svg\'>' + content + '</svg>';
    var document = parser.parseFromString(content, 'text/xml').documentElement;
    return document.firstChild;
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
    var keyCodeE = 69;
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
        if (ctrlDown && e.keyCode == keyCodeE) {
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

jQuery.fn.serializeForm = function() {
    // Create an object to hold the data
    var formParams = {};

    // Iterate over the selector results
    this.each(function() {
        // Iterate over each child form element of this
        jQuery('input, select, textarea', this).each(function() {
            var jObject = jQuery(this);
            var id = jObject.attr('id');
            if (id != null && id != '') {       // Only add elements with an id
                var data = jObject.val();
                var type = jObject.attr('type');
                if ('checkbox' == type) {
                    formParams[id] = this.checked ? 'true' : 'false';
                } else if ('radio' == 'type') {
                    formParams[id] = data;
                } else if (formParams[id] === undefined) {
                    formParams[id] = encodeURIComponent(data);
                } else {        // Append
                    formParams[id] += ',' + encodeURIComponent(data);
                }
            }
        });
    });
    return formParams;
};