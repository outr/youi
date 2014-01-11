HyperscalaConnect = (function() {
    var messageId = 0;
    var pageId = null;
    var connectionId = null;
    var handlers = {};
    var queue = [];
    var sentQueue = [];

    var sendSuccess = function(data) {
        if (data.status == 'OK') {
            respond(false);          // Send more data or wait for more data
        } else {
            console.log('Send successfully, but incorrect respond data: ' + JSON.stringify(data) + ', retrying in five seconds!');
            setTimeout(function() {
                respond(true);
            }, 5000);
        }
    };
    var sendError = function(jqXHR, textStatus, errorThrown) {
        if (errorThrown == 'Not Found') {
            console.log('Page not found. Reloading page...');
            location.reload();
        } else if (errorThrown == '') {
            console.log('Unable to connect. Retrying in five seconds...');
            setTimeout(function() {
                respond(true);
            }, 5000);
        } else {
            console.log('Receive Error: ' + textStatus + ' - ' + errorThrown + '. Unhandled failure.');
        }
    };
    var sendComplete = function(event) {
    };
    var sendSettings = {
        type: 'POST',
        url: '/hyperscala.connect/send',
        dataType: 'json',
        processData: false,
        cache: false,
        async: true,
        success: sendSuccess,
        error: sendError,
        complete: sendComplete,
        timeout: 15000
    };

    var receiveSuccess = function(data) {
        if (data.status == 'OK') {
            var allHandlers = handlers['*'];
            for (var index = 0; index < data.messages.length; index++) {
                var message = data.messages[index];
                var array = handlers[message.event];
                receive(message.event, message.data, array, false);
                receive(message.event, message.data, allHandlers, true);
            }
            request(false);
        } else {
            console.log('Received successfully, but incorrect data: ' + JSON.stringify(data) + ', retrying in five seconds!');
            setTimeout(function() {
                request(true);
            }, 5000);
        }
    };
    var receiveError = function(jqXHR, textStatus, errorThrown) {
        if (errorThrown == 'Not Found') {
            console.log('Page not found. Reloading page...');
            location.reload();
        } else if (errorThrown == '') {
            console.log('Unable to connect. Retrying in five seconds...');
            setTimeout(function() {
                request(true);
            }, 5000);
        } else {
            console.log('Receive Error: ' + textStatus + ' - ' + errorThrown + '. Unhandled failure.');
        }
    };
    var receiveComplete = function(event) {
    };
    var receiveSettings = {
        type: 'POST',
        url: '/hyperscala.connect/receive',
        dataType: 'json',
        processData: false,
        cache: false,
        async: true,
        success: receiveSuccess,
        error: receiveError,
        complete: receiveComplete,
        timeout: 60000
    };

    var init = function(pid, cid) {
        pageId = pid;
        connectionId = cid;

        request(false);          // Request data from the server
        respond(false);          // Send data to the server
    };
    var send = function(event, data) {
        queue.push({
            id: ++messageId,
            event: event,
            data: data
        });
    };
    var on = function(event, handler) {
        if (event == null) {
            event = '*';
        }
        if (handlers[event] == null) {
            handlers[event] = [];
        }
        var array = handlers[event];
        array.push(handler);
    };

    // Internal functions

    var request = function(resend) {
        var data = {
            pageId: pageId,
            connectionId: connectionId,
            timestamp: new Date().getTime(),
            resend: resend
        };
        receiveSettings.data = JSON.stringify(data);
        $.ajax(receiveSettings);
    };
    var respond = function(resend) {
        if (resend) {                       // Failure, so lets send the queue again
            sendQueue(sentQueue);
        } else if (queue.length > 0) {      // Queue isn't empty, lets send it
            sendQueue(queue);
            sentQueue = queue;
            queue = [];
        } else {                            // Queue is empty, lets check back later
            setTimeout(function() {
                respond(false);
            }, 10);
        }
    };
    /**
     * Receives an event with data and calls the handlers within the supplied array.
     *
     * @param event
     * @param data
     * @param array
     * @param includeEvent
     */
    var receive = function(event, data, array, includeEvent) {
        if (array != null) {
            for (var index = 0; index < array.length; index++) {
                var handler = array[index];
                try {
                    if (includeEvent) {
                        handler(event, data);
                    } else {
                        handler(data);
                    }
                } catch(err) {
                    console.log('Error calling handler to receive: ' + event + ' - ' + err.message + ' (' + handler + ')');
                }
            }
        }
    };
    /**
     * Sends the supplied queue to the server.
     *
     * @param queue
     */
    var sendQueue = function(queue) {
        var data = {
            pageId: pageId,
            connectionId: connectionId,
            timestamp: new Date().getTime(),
            messages: queue
        };
        sendSettings.data = JSON.stringify(data);
        $.ajax(sendSettings);
    };

    return {
        init: init,
        send: send,
        on: on
    };
})();