HyperscalaConnect.on('eval', function(data) {
    try {
        realtimeEvaluate(data, false);
    } catch(err) {
        log('Failed to evaluate instruction: ' + JSON.stringify(data) + ' - ' + err);
    }
});

/**
 * Connects the Communicator with a RealtimePage on the server and allows asynchronous
 * communication to occur.
 *
 * @param pageId
 * @param debug
 */
function connectRealtime(pageId, debug) {
    communicator.connect({
        createData: {
            pageId: pageId
        },
        on: {
            connected: function() {
                console.log('Connection established!');
            },
            error: function(err) {
                console.log('Error occurred: ' + err);
            },
            eval: function(msg) {
                try {
                    if (typeof msg == 'string') {           // Parse to JSON if it isn't already
                        msg = jQuery.parseJSON(msg);
                    }
                } catch(err) {
                    log('Unable to parse JSON [' + msg + ']');
                }
                try {
                    realtimeEvaluate(msg, debug);
                } catch(err) {
                    log('Failed to evaluate instruction: ' + msg + ' - ' + err);
                }
            }
        }
    });
}

/**
 * Add this call to a JavaScript event to fire the event down to the server.
 *
 * @param event
 * @param data
 * @param confirmation
 * @param preventDefault
 * @param fireChange
 * @param onlyLast
 * @param delay
 */
function realtimeEvent(event, data, confirmation, preventDefault, fireChange, onlyLast, delay) {
    try {
        var element = $(event.currentTarget);
        var id = element.attr('id');
        if (id == null) {
            element = $(event.target);
            id = element.attr('id');
            if (id == null) {
                element = $(event.srcElement);
                id = element.attr('id');
            }
        }

        if (id != null) {
            var eventType = event.type;
            var content = {
                id: id
            };

            if ('keydown, keypress, keyup'.indexOf(eventType) != -1) {
                realtimeUpdateKeyEvent(event, content);
            } else if (eventType == 'change') {
                content.value = realtimeChangeEventValue(element);
            }

            var f = function() {
                if (fireChange) {
                    realtimeSend(id, 'change', {
                        value: realtimeChangeEventValue(element)
                    })
                }
                HyperscalaConnect.send(eventType, content);
            };

            if (confirmation == null || confirm(confirmation)) {
                if (delay != 0) {
                    setTimeout(f, delay);
                } else {
                    f();
                }
            }

            return !preventDefault;
        }
    } catch(err) {
        // TODO: add support to send errors to the server (if possible)
        alert('An error occurred: ' + err.message);
    }
}

function realtimeSend(id, eventType, content) {
    if (typeof content == 'string') {     // Make sure we're sending JSON, not stringified JSON
        content = jQuery.parseJSON(content);
    }
    var data = {
        id: id
    };
    jQuery.extend(data, content);
    HyperscalaConnect.send(eventType, data);
}

/**
 * Retrieves the value for a change event for the supplied element.
 *
 * @param element
 */
function realtimeChangeEventValue(element) {
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
    return value;
}

/**
 * Called to update 'content' JSON with key event data.
 *
 * @param event
 * @param content
 */
function realtimeUpdateKeyEvent(event, content) {
    content.altKey = event.altKey;
    content.char = event.charCode;
    content.ctrlKey = event.ctrlKey;
    content.key = event.keyCode;
    content.locale = event.locale;
    content.location = event.location;
    content.metaKey = event.metaKey;
    content.repeat = event.repeat;
    content.shiftKey = event.shiftKey;
}

function realtimeEvaluate(json, debug) {
    var content = json['content'];
    var instruction = json['instruction'];
    var selector = json['selector'];
    var delay = json['delay'];
    if (delay > 0) {                                // Handle delay if specified
        json['delay'] = 0;
        setTimeout(function() {
            realtimeEvaluate(json, debug);
        }, delay);
    } else if (selector != null) {
        if (eval('$(' + selector + ').length') == 0) {              // Selector returned empty, wait a few milliseconds and check again
            if (debug) {
                log('Selector: ' + selector + ' returned empty...waiting...');
            }
            setTimeout(function() {
                realtimeEvaluate(json, debug);
            }, 10);
        } else {                                    // Selector has items, call again without selector
            json['selector'] = null;
            realtimeEvaluate(json, debug);
        }
    } else {
        try {
            if (debug) {
                log('evaluating: ' + instruction + ' (content: ' + content + ')');
            }
            eval(instruction);
        } catch(err) {
            log('Error occurred (' + err.message + ') while attempting to evaluate instruction: [' + instruction + '] with content: [' + content + '].')
        }
    }
}

function log(msg) {
    var message = new Date().toLocaleString() + ': ' + msg;
    console.log(message);
}

// TODO: remove this in favor of a completely new SVG implementation
function parseSVG(content) {
    var parser = new DOMParser();
    parser.async = false;
    content = content.toString().trim();
    content = '<svg xmlns=\'http://www.w3.org/2000/svg\'>' + content + '</svg>';
    var document = parser.parseFromString(content, 'text/xml').documentElement;
    return document.firstChild;
}

var realtimeGroup = {};         // Used for grouping

/**
 * Combines messages of a specific id (only sends the last one) and sends at maximum every timeout.
 *
 * @param groupId
 * @param timeout
 * @param event
 * @param id
 * @param message
 */
function groupedSend(groupId, timeout, event, id, message) {
    var current = (new Date).getTime();
    var group = realtimeGroup[groupId];
    if (group == null) {            // Set up group if not already defined
        group = {
            lastSend: 0
        };
        realtimeGroup[groupId] = group;
    }
    group.event = event;
    group.id = id;
    group.message = message;
    if (group.timeoutId == null) {          // Not currently waiting to send something
        var delay = group.lastSend - current + timeout;
        if (delay <= 0) {
            delay = 0;
        }
        group.timeoutId = window.setTimeout(function() {
            delayedGroupSend(groupId);
        }, delay);
    }
}

/**
 * Called internally by groupedSend when a groupId times out.
 *
 * @param groupId
 */
function delayedGroupSend(groupId) {
    var group = realtimeGroup[groupId];
    if (group != null) {
        realtimeSend(group.id, group.event, group.message);
        group.timeoutId = null;             // Remove the timeout id so we know it has run
        group.lastSend = (new Date).getTime();
    }
}

// Support serializing a form
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