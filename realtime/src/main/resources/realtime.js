var realtime = {
    /**
     * The Communicate instance. Created on call to 'init'.
     */
    communicate: null,
    /**
     * Message / Event listeners object.
     */
    listeners: {},
    /**
     * Debug mode logs enabled logs additional information to the console in the browser.
     */
    debug: false,
    /**
     * Called by Realtime to initialize the communication connection.
     *
     * @param settings the settings information for Realtime and Communicate
     */
    init: function(settings) {
        this.debug = settings.debug;

        var r = this;
        this.communicate = new Communicate(settings);
        this.communicate.send({                         // Initialize the connection with Realtime
            type: 'init',
            siteId: settings.siteId,
            pageId: settings.pageId
        });
        this.communicate.on('json', function(obj) {
            if (obj.type != null) {
                r.fire(obj.type, obj);
                r.fire('*', obj);
                r.log('Received JSON', obj);
            }
        });
        this.communicate.on('open', function(evt) {
            r.fire('open', evt);
            r.log('Connection opened.');
        });
        this.communicate.on('close', function(evt) {
            r.fire('close', evt);
            r.log('Connection closed.');
        });
        this.communicate.on('error', function(evt) {
            r.fire('error', evt);
            r.log('Error occurred.', evt.data);
        });
        this.communicate.connect();
    },
    /**
     * Sends a message to the server. Non-String values are stringified via JSON.stringify before sending.
     *
     * @param message the message to send
     */
    send: function(message) {
        if (this.communicate == null) {
            this.error('Unable to send message, Realtime has not yet initialized!', message);
        } else {
            this.communicate.send(message);
        }
    },
    /**
     * Fire an event to the listeners.
     *
     * @param type the listeners group type
     * @param obj the event that is actually fired
     */
    fire: function(type, obj) {
        if (this.listeners[type]) {
            for (var i = 0; i < this.listeners[type].length; i++) {
                this.listeners[type][i](obj);
            }
        }
    },
    /**
     * Add a listener to a specific type of event.
     *
     * @param type the listeners group type
     * @param handler the function handler for events
     */
    on: function(type, handler) {
        if (this.listeners[type] == null) {
            this.listeners[type] = [];
        }
        this.listeners[type].push(handler);
    },
    /**
     * Used for debug logging. Calls to this function are ignored if 'debug' is disabled.
     *
     * @param message the message to log
     * @param obj optional object that is stringified on the end if provided
     */
    log: function(message, obj) {
        if (this.debug) {
            console.log('Realtime (' + new Date() + '): ' + message + (obj != null ? ' - ' + JSON.stringify(obj) : ''));
        }
    },
    error: function(message, obj) {
        console.log('Realtime (' + new Date() + '): ' + message + (obj != null ? ' - ' + JSON.stringify(obj) : ''));
        // TODO: send message to server via AJAX call!
    },
    event: function(evt, data, confirmMessage, preventDefault, fireChange, delay) {
        try {
            if (evt.src) evt.target = evt.srcElement;       // Handling for older versions of IE

            var element = evt.currentTarget;                // Current target is where the listener was added, target is what fired it
            var id = element.getAttribute('id');

            if (id != null) {
                var content = {
                    id: id,
                    type: evt.type,
                    target: evt.target.getAttribute('id'),
                    data: data
                };
                if ('keydown, keypress, keyup'.indexOf(evt.type) != -1) {
                    content.altKey = evt.altKey;
                    content.char = evt.charCode;
                    content.ctrlKey = evt.ctrlKey;
                    content.key = evt.keyCode;
                    content.locale = evt.locale;
                    content.location = evt.location;
                    content.metaKey = evt.metaKey;
                    content.repeat = evt.repeat;
                    content.shiftKey = evt.shiftKey;
                } else if (evt.type == 'change') {
                    content.value = this.changeValue(element);
                }

                var r = this;
                var f = function() {
                    if (fireChange) {
                        r.send(jQuery.extend(content, {
                            type: 'change',
                            value: r.changeValue(element)
                        }));
                    }
                    r.send(content);
                };

                if (confirmMessage == null || confirm(confirmMessage)) {
                    if (delay != 0) {
                        setTimeout(f, delay);
                    } else {
                        f();
                    }
                }
            } else {
                this.error('Element ID is null for realtime.event.' + evt.type);
            }
        } catch(err) {
            this.error('Error occurred handling a JavaScript event: ' + err.message);
        }
    },
    changeValue: function(element) {
        var tagName = element.tagName.toLowerCase();
        var value = null;
        if (tagName == 'input') {
            var type = element.type.toLowerCase();
            if (type == 'text') {
                value = element.value;
            } else if (type == 'checkbox' || type == 'radio') {
                value = element.checked;
            } else {
                this.error('Unsupported input type for change event: ' + type);
            }
        } else if (tagName == 'form') {
            var formData = $(element).serializeForm();
            value = '';
            jQuery.each(formData, function(k, v) {
                if (value != '') {
                    value += '&';
                }
                value += k;
                value += '=';
                value += v;
            });
        } else {
            this.error('Unsupported tag for change event: ' + tagName);
        }
        return value;
    }
};

// Handle page reload support
realtime.on('reload', function(obj) {
    realtime.log('Reloading page...');
    document.location.reload(obj.forcedReload);
});