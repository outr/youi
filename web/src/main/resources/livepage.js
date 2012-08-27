// Unique identifier to tie to LiveConnection
var liveId = '%1$s';
// Last message id received
var liveMessageId = %2$s;
// Storage of identifiable references
var liveReferences = {};
// Set to true if currently in the process of an AJAX call
var liveSending = false;
// Message queue
var liveQueue = [];
// Temporary holder for creating elements
var liveDiv = document.createElement('div');

// Returns identifiable object by id
function liveLookup(id) {
    // TODO: support cleanup of gc'd objects for long-running apps
    if (liveReferences[id] == null) {       // Doesn't exist in references
        liveReferences[id] = $('#' + id);
    }
    return liveReferences[id];
}

// Creates a tag or text node for reference by id
function liveCreate(id, tagContent) {
    liveDiv.innerHTML = tagContent;
    liveReferences[id] = $(liveDiv.firstChild);
}

// Inserts the element by id into the parent by parentId at index
function liveInsert(parentId, id, index) {
    var parent = liveLookup(parentId);
    var child = liveLookup(id);
    parent.insertAt(index, child);
}

// Inserts the text into the parent by parentId at index
function liveInsertText(parentId, text, index) {
    var parent = liveLookup(parentId);
    parent.insertAt(index, text);
}

// Enqueue a JSON message
function liveMessage(message) {
    liveQueue.push(message);
    liveSend();     // Try to send immediately
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
    liveEvent(element.attr('id'), e.type);
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
    if (data != null) {
        for (var i = 0; i < data.length; i++) {
            var id = data[i]['id'];
            var script = data[i]['script'];
            console.log('evaluating: ' + script);
            eval(script);
            liveMessageId = Math.max(id, liveMessageId);
        }
    } else {
        console.log('Response was null - stopping updates');
        clearInterval(liveTimer);
    }
}

function liveSendFailure() {
    // TODO: attempt retry of send
    console.log('live send failure!');
}

function liveSendComplete() {
    liveSending = false;
}

jQuery.fn.insertAt = function(index, element) {
    var lastIndex = this.children().size();
    if (index < 0) {
        index = Math.max(0, lastIndex + 1 + index);
    }
    this.append(element);
    if (index < lastIndex) {
        this.children().eq(index).before(this.children().last());
    }
    return this;
};

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

var liveTimer = setInterval(liveSend, 5000);