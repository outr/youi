var communicator = new Communicator();

/**
 * Connects the Communicator with a RealtimePage on the server and allows asynchronous
 * communication to occur.
 *
 * @param pageId
 */
function connectRealtime(pageId) {
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
            received: function(msg) {
                console.log('Received: ' + JSON.stringify(msg));
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
                id: id,
                eventType: eventType
            };

            if ('keydown, keypress, keyup'.indexOf(eventType) != -1) {
                realtimeUpdateKeyEvent(event, content);
            } else if (eventType == 'change') {
                content.value = realtimeChangeEventValue(element);
            }

            var f = function() {
                if (fireChange) {
                    var changeContent = {
                        id: id,
                        eventType: 'change',
                        value: realtimeChangeEventValue(element)
                    };
                    communicator.send('realtime', changeContent);
                }
                communicator.send('realtime', content);
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