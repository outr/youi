// Unique identifier to tie to LiveConnection
var liveId = '%1$s';
// Last message id received
var liveMessageId = %2$s;
// Set to true if currently in the process of an AJAX call
var liveSending = false;
// Message queue
var liveQueue = [];
// Currently sending data
var liveData = null;
// Number of times the AJAX send has failed
var failures = 0;
// Maximum number of times the send can fail before it gives up
var maxFailures = %3$s;
// Debug mode defines whether additional information is output to the console logging.
var debugMode = %5$s;

function liveAdd(parentId, index, tagContent) {
    var parent = $('#' + parentId);
    liveInsertAt(parent, index, content);
}

function liveRemove(id) {
    var element = $('#' + id);
    element.remove();
}

function liveRemoveByIndex(parentId, index) {
    var parent = $('#' + parentId);
    parent.contents().eq(index).remove();
}

// Enqueue a JSON message
function liveMessage(message) {
    liveEnqueue(message);
    liveSend();     // Try to send immediately
}

function liveEnqueue(message) {
    liveQueue.push(message);
}

// Fire an event to the server on a specified id
function liveEvent(id, event) {
    liveMessage({
        'id': id,
        'type': 'event',
        'event': event
    });
}

// Add as a handler to fire live events to server
function liveEventHandler(e) {
    var element = $(e.target);
    var id = element.attr('id');
    if (e.type == 'change') {
        liveEnqueue({
            'id': id,
            'type': 'change',
            'value': element.val()
        });
    }
    liveEvent(id, e.type);
}

// Sends enqueued messages to the server
function liveSend() {
    if (!liveSending) {
        liveSending = true;
        var message = {
            'liveId': liveId,
            'liveMessageId': liveMessageId,
            'messages': liveQueue
        };
        liveData = liveQueue;       // Hold onto the data just in case we have to retry
        liveQueue = [];     // Reset live queue
        var url = window.location.href;
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            async: true,
            data: JSON.stringify(message),
            processData: false,
            cache: false,
            success: liveSendSuccessful,
            error: liveSendFailure,
            complete: liveSendComplete
        });
    }
}

function liveSendSuccessful(data) {
//    console.log('live send successful!');
    failures = 0;       // Reset failure counter
    if (data != null) {
        for (var i = 0; i < data.length; i++) {
            var id = data[i]['id'];
            var script = data[i]['script'];
            if (debugMode) {
                console.log('evaluating:[' + script + ']');
            }
            eval(script);
            liveMessageId = Math.max(id, liveMessageId);
        }
    } else {
        console.log('Response was null - stopping updates');
        clearInterval(liveTimer);
    }
}

function liveSendFailure() {
    console.log('live send failure!');
    failures++;
    if (failures < maxFailures) {
        liveQueue = liveData.concat(liveQueue);     // Join the attempted data back to retry
    } else {
        console.log('live send failed ' + failures + ' times - stopping updates');
        clearInterval(liveTimer);
    }
}

function liveSendComplete() {
    liveSending = false;
    if (liveQueue.length > 0 && failures == 0) {
        liveSend();     // There's more in the queue to send
    }
}

function liveInsertAt(parent, index, element) {
    var lastIndex = parent.children().size();
    if (index < 0) {
        index = Math.max(0, lastIndex + 1, index);
    }
    parent.append(element);
    if (index < lastIndex) {
        parent.children().eq(index).before(parent.children().last());
    }
}

JSON.stringify = JSON.stringify || function (obj) {
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        // simple data type
        if (t == "string") obj = '"'+obj+'"';
        return String(obj);
    }
    else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);
        for (n in obj) {
            v = obj[n]; t = typeof(v);
            if (t == "string") v = '"'+v+'"';
            else if (t == "object" && v !== null) v = JSON.stringify(v);
            json.push((arr ? "" : '"' + n + '":') + String(v));
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};

liveSend(); // Send immediately just in case we missed something
var liveTimer = setInterval(liveSend, %4$s);