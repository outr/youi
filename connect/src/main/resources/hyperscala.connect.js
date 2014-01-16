HyperscalaConnect = (function() {
    var messageId = 0;
    var pageId = null;
    var connectionId = null;
    var handlers = {};
    var queue = [];
    var sentQueue = [];
    var lastReceiveId = 0;
    var disconnected = false;
    var maxRetries = 10;
    var sendFailures = 0;
    var receiveFailures = 0;
    var debug = false;

    var errorDisposed = false;
    var errorDiv = $('#hyperscala_connect_error');
    var errorClose = $('#hyperscala_connect_error_close');
    var errorMessage = $('#hyperscala_connect_error_message');

    var showErrorDialog = function(title, text) {
        updateErrorDialog(title, text);
        if (!errorDisposed) {
            errorDiv.removeClass('hyperscala_connect_error_hidden');
        }
    };
    var updateErrorDialog = function(title, text) {
        console.log('ErrorMessage: ' + errorMessage.length + ', ErrorDiv: ' + errorDiv.length);
        errorMessage.html('<h2>' + title + '</h2><p>' + text + '</p>');
    };
    var closeErrorDialog = function() {
        errorDisposed = false;          // Reset the disposed status
        errorDiv.addClass('hyperscala_connect_error_hidden');
    };
    errorClose.click(function() {
        closeErrorDialog();
        errorDisposed = true;
    });

    var sendSuccess = function(data) {
        if (data.status == 'OK') {
            sendFailures = 0;
            closeErrorDialog();
            respond(false);          // Send more data or wait for more data
        } else if (!disconnected) {
            sendFailures++;

            showErrorDialog('Bad Response Data', 'The server gave us some bad data. Let\'s keep trying...');
            console.log('Send successfully, but incorrect respond data: ' + JSON.stringify(data, undefined, 2) + ', retrying in five seconds!');
            setTimeout(function() {
                respond(true);
            }, 5000);
        }
    };
    var sendError = function(jqXHR, textStatus, errorThrown) {
        if (!disconnected) {
            sendFailures++;
            if (errorThrown == 'Not Found') {
                showErrorDialog('Page Not Found', 'Unable to find the needed page, let\'s reload...');
                console.log('Page not found. Reloading page...');
                reload();
            } else if (errorThrown == '') {
                showErrorDialog('Unable to Connect', 'Unable to establish a connection to the server. Let\'s keep trying...');
                console.log('Unable to connect. Retrying in five seconds...');
                setTimeout(function() {
                    respond(true);
                }, 5000);
            } else {
                showErrorDialog('Receive Error', 'The server threw an error. Sorry about that, we\'ll reload the page for you...');
                console.log('Receive Error: ' + textStatus + ' - ' + errorThrown + '. Unhandled failure. Reloading page in fifteen seconds...');
                disconnect();
                setTimeout(function() {
                    reload();
                }, 5000);
            }
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
    var disconnect = function() {
        showErrorDialog('Disconnected', 'The connection to the server has been terminated. Please reload the page to reconnect.');
        console.log('Disconnecting');
        disconnected = true;
    };
    var reload = function() {
        if (debug) {
            console.log('Not reloading, in debug mode!');
        } else {
            location.reload();
        }
    };

    var receiveSuccess = function(data) {
        if (debug) console.log('server -> client: ' + JSON.stringify(data, undefined, 2));
        if (data.status == 'OK') {
            closeErrorDialog();
            receiveFailures = 0;
            var allHandlers = handlers['*'];
            for (var index = 0; index < data.messages.length; index++) {
                var message = data.messages[index];
                var expectedId = lastReceiveId + 1;
                if (message.id == expectedId) {
                    var array = handlers[message.event];
                    lastReceiveId = expectedId;
                    receive(message.event, message.data, array, false);
                    receive(message.event, message.data, allHandlers, true);
                } else if (message.id < expectedId) {
                    console.log('Ignoring message: ' + message.event + '. Expected ID: ' + expectedId + ', Message ID: ' + message.id);
                } else if (message.id > expectedId) {
                    showErrorDialog('Corrupted Data', 'The server didn\'t send us all the data, we need to reload...');
                    console.log('Lost a message! Expected: ' + expectedId + ', Received: ' + message.id + ' Reloading in five seconds...');
                    disconnect();
                    setTimeout(function() {
                        reload();
                    }, 5000);
                }
            }
            request(false);
        } else if (!disconnected) {
            receiveFailures++;
            showErrorDialog('Bad Response Data', 'The server gave us some bad data. Let\'s keep trying...');
            console.log('Received successfully, but incorrect data: ' + JSON.stringify(data, undefined, 2) + ', retrying in five seconds!');
            setTimeout(function() {
                request(true);
            }, 5000);
        }
    };
    var receiveError = function(jqXHR, textStatus, errorThrown) {
        if (!disconnected) {
            receiveFailures++;
            if (errorThrown == 'Not Found') {
                showErrorDialog('Page Not Found', 'Unable to find the needed page, let\'s reload...');
                console.log('Page not found. Reloading page...');
                reload();
            } else if (errorThrown == 'SyntaxError: Unexpected token <') {
                showErrorDialog('Unexpected Page Data', 'Looks like the server may have restarted, let\'s reload...');
                console.log('Invalid page content. Reloading page...');
                reload();
            } else if (errorThrown == '') {
                showErrorDialog('Unable to Connect', 'Unable to establish a connection to the server. Let\'s keep trying...');
                console.log('Unable to connect. Retrying in five seconds...');
                setTimeout(function() {
                    request(true);
                }, 5000);
            } else {
                showErrorDialog('Receive Error', 'The server threw an error. Sorry about that, we\'ll reload the page for you...');
                console.log('Receive Error: ' + textStatus + ' - ' + errorThrown + '. Unhandled failure. Reloading page in fifteen seconds...');
                disconnect();
                setTimeout(function() {
                    reload();
                }, 15000);
            }
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

        errorDiv = $('#hyperscala_connect_error');
        errorClose = $('#hyperscala_connect_error_close');
        errorMessage = $('#hyperscala_connect_error_message');

        request(false);          // Request data from the server
        respond(false);          // Send data to the server

        window.onbeforeunload = function() {
            closeErrorDialog();
            errorDisposed = true;
            disconnect();
        };
        window.onunload = function() {
            closeErrorDialog();
            errorDisposed = true;
            disconnect();
        };
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
        if (!disconnected) {
            if (receiveFailures >= maxRetries) {
                console.log('Maximum receive failures reached. Disconnecting.');
                disconnect();
            } else {
                var data = {
                    pageId: pageId,
                    connectionId: connectionId,
                    timestamp: new Date().getTime(),
                    resend: resend
                };
                receiveSettings.data = JSON.stringify(data);
                $.ajax(receiveSettings);
            }
        }
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
        if (!disconnected) {
            if (sendFailures >= maxRetries) {
                console.log('Maximum send failures reached. Disconnecting.');
                disconnect();
            } else {
                var data = {
                    pageId: pageId,
                    connectionId: connectionId,
                    timestamp: new Date().getTime(),
                    messages: queue
                };
                sendSettings.data = JSON.stringify(data);
                if (debug) console.log('client -> server: ' + sendSettings.data);
                $.ajax(sendSettings);
            }
        }
    };

    return {
        init: init,
        send: send,
        on: on
    };
})();