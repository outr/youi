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
        try {
            this.debug = settings.debug;

            var r = this;
            this.communicate = new Communicate(settings);
            this.communicate.send({                         // Initialize the connection with Realtime
                type: 'init',
                siteId: settings.siteId,
                pageId: settings.pageId
            });
            this.communicate.on('json', function (obj) {
                if (obj.type != null) {
                    r.fire(obj.type, obj);
                    r.fire('*', obj);
                    //r.log('Received JSON', obj);
                }
            });
            this.communicate.on('open', function (evt) {
                r.fire('open', evt);
                r.log('Connection opened.');
            });
            this.communicate.on('close', function (evt) {
                r.fire('close', evt);
                r.log('Connection closed.');
            });
            this.communicate.on('error', function (evt) {
                r.fire('error', evt);
                r.log('Error occurred.', evt.data);
            });
            this.communicate.connect();
        } catch(err) {
            realtime.error('Failed to init', settings, err);
        }
    },
    /**
     * Sends a message to the server. Non-String values are stringified via JSON.stringify before sending.
     *
     * @param message the message to send
     */
    send: function(message) {
        realtime.log('Sending message.', message);
        if (this.communicate == null) {
            realtime.error('Unable to send message, Realtime has not yet initialized!', message);
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
        try {
            if (this.listeners[type] && this.listeners[type].length > 0) {
                for (var i = 0; i < this.listeners[type].length; i++) {
                    this.listeners[type][i](obj);
                }
            } else if ('open, close, *'.indexOf(type) == -1) {
                realtime.error('Nothing listening for events of type [' + type + '].', obj);
            }
        } catch(err) {
            realtime.error('Failed to fire event ' + type + ' to listeners because ' + err.message, obj, err);
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
    error: function(message, obj, err) {
        console.log('Realtime (' + new Date() + '): ' + message + (obj != null ? ' - ' + JSON.stringify(obj) : ''));
        if (err != null) console.log(err.stack);
        this.send({
            type: 'error',
            timestamp: Date.now(),
            message: message,
            obj: JSON.stringify(obj),
            errorMessage: err != null ? err.message : null,
            stackTrace: err != null ? err.stack : null
        });
    },
    event: function(evt, confirmMessage, preventDefault, fireChange, delay) {
        try {
            if (evt.src) evt.target = evt.srcElement;       // Handling for older versions of IE

            var element = evt.currentTarget;                // Current target is where the listener was added, target is what fired it
            var id = element.getAttribute('id');

            if (id != null) {
                var content = {
                    id: id,
                    type: evt.type,
                    target: evt.target.getAttribute('id')
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
                        r.fireChange(element);
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
                return !preventDefault;
            } else {
                realtime.error('Element ID is null for realtime.event.' + evt.type);
            }
        } catch(err) {
            realtime.error('Error occurred handling a JavaScript event: ' + err.message, null, err);
        }
    },
    fireChange: function(element) {
        realtime.send({
            type: 'change',
            id: element.getAttribute('id'),
            value: realtime.changeValue(element)
        });
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
                realtime.error('Unsupported input type for change event: ' + type);
            }
        } else if (tagName == 'textarea') {
            value = element.value;
        } else if (tagName == 'select') {
            value = element.value;
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
            realtime.error('Unsupported tag for change event: ' + tagName);
        }
        return value;
    }
};

// Handle page reload support
realtime.on('reload', function(obj) {
    try {
        realtime.log('Reloading page...');
        document.location.reload(obj.forcedReload);
    } catch(err) {
        realtime.error('Failed to handle reload request', obj, err);
    }
});

// Handle InsertHTML
realtime.on('insertHTML', function(obj) {
    try {
        realtime.log('Insert HTML after: ' + obj.after + ', parent: ' + obj.parent + ', HTML: ' + obj.html);
        if (obj.after != null) {
            var after = $('#' + obj.after);
            if (after.length == 0) {
                realtime.error('Unable to insert HTML after ' + obj.after + ' as the element is not in the hierarchy.');
            } else {
                after.after(obj.html);
            }
        } else {
            var parent = $('#' + obj.parent);
            if (parent.length == 0) {
                realtime.error('Unable to insert HTML under parent ' + obj.parent + ' as the element is not in the hierarchy.');
            } else {
                parent.append(obj.html);
            }
        }
    } catch(err) {
        realtime.error('Failed to handle insertHTML request', obj, err);
    }
});

// Handle RemoveHTML
realtime.on('removeHTML', function(obj) {
    try {
        realtime.log('Remove HTML! ' + obj.id);
        var element = document.getElementById(obj.id);
        if (element == null) {
            realtime.error('Unable to find element by id: ' + obj.id + ' remove.');
        } else {
            element.parentNode.removeChild(element);
        }
    } catch(err) {
        realtime.error('Failed to handle removeHTML request', obj, err);
    }
});

// Handle setting HTML attributes
realtime.on('attributeHTML', function(obj) {
    try {
        var element = document.getElementById(obj.id);
        if (element == null) {
            realtime.error('Unable to find element by id: ' + obj.id + ' to set attribute ' + obj.key + ' = ' + obj.value);
        } else {
            var tagName = element.tagName.toLowerCase();
            if (obj.value == null) {
                element.removeAttribute(obj.key);
            } else if (obj.key == 'content') {
                if (tagName == 'textarea') {
                    $(element).val(obj.value);
                } else {
                    console.log('Setting inner html: ' + obj.value);
                    element.innerHTML = obj.value;
                }
            } else {
                realtime.log('setting attribute: ' + obj.key + ' = ' + obj.value);
                if (obj.key == 'value') {
                    $(element).val(obj.value);
                } else {
                    element.setAttribute(obj.key, obj.value);
                    if (tagName == 'option' && obj.key == 'selected') {
                        element.parentNode.value = element.value;
                    }
                }
            }
        }
    } catch(err) {
        realtime.error('Failed to handle attributeHTML request', obj, err);
    }
});

// Handle selector stylization
realtime.on('setStyle', function(obj) {
    try {
        realtime.log('setting style!', obj);
        if (obj.styleSheet) {
            if (obj.key == null) {          // Clear style sheet
                $.stylesheet(obj.selector).css(null);
            } else {
                $.stylesheet(obj.selector, obj.key, obj.value);
            }
        } else {
            var selector = $(obj.selector);
            if (selector.length == 0) {
                realtime.error('Unable to find element by selector: ' + obj.selector + ' to set style ' + obj.key + ' = ' + obj.value);
            } else {
                if (obj.important) {
                    selector.style(obj.key, obj.value, 'important');
                } else {
                    selector.style(obj.key, obj.value);
                }
            }
        }
    } catch(err) {
        realtime.error('Failed to handle setStyle request', obj, err);
    }
});

// Handle evaluating arbitrary JavaScript from the server
realtime.on('eval', function(obj) {
    realtime.log('evaluating: ' + obj.code);
    var f = function() {
        if (obj.waitCondition == null || eval(obj.waitCondition)) {
            try {
                eval(obj.code);
            } catch(err) {
                realtime.error('Failed to handle eval request (' + err.message + ') - ' + obj.code, null, err);
            }
        } else {
            setTimeout(f, 10);
        }
    };
    f();
});

// Add support for the 'style' alternative to 'css' to allow setting importance
(function($) {
    if ($.fn.style) {
        return;
    }

    // Escape regex chars with \
    var escape = function(text) {
        return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
    };

    // For those who need them (< IE 9), add support for CSS functions
    var isStyleFuncSupported = !!CSSStyleDeclaration.prototype.getPropertyValue;
    if (!isStyleFuncSupported) {
        CSSStyleDeclaration.prototype.getPropertyValue = function(a) {
            return this.getAttribute(a);
        };
        CSSStyleDeclaration.prototype.setProperty = function(styleName, value, priority) {
            this.setAttribute(styleName, value);
            var priority = typeof priority != 'undefined' ? priority : '';
            if (priority != '') {
                // Add priority manually
                var rule = new RegExp(escape(styleName) + '\\s*:\\s*' + escape(value) +
                '(\\s*;)?', 'gmi');
                this.cssText =
                    this.cssText.replace(rule, styleName + ': ' + value + ' !' + priority + ';');
            }
        };
        CSSStyleDeclaration.prototype.removeProperty = function(a) {
            return this.removeAttribute(a);
        };
        CSSStyleDeclaration.prototype.getPropertyPriority = function(styleName) {
            var rule = new RegExp(escape(styleName) + '\\s*:\\s*[^\\s]*\\s*!important(\\s*;)?',
                'gmi');
            return rule.test(this.cssText) ? 'important' : '';
        }
    }

    // The style function
    $.fn.style = function(styleName, value, priority) {
        // DOM node
        var node = this.get(0);
        // Ensure we have a DOM node
        if (typeof node == 'undefined') {
            return this;
        }
        // CSSStyleDeclaration
        var style = this.get(0).style;
        // Getter/Setter
        if (typeof styleName != 'undefined') {
            if (typeof value != 'undefined') {
                // Set style property
                priority = typeof priority != 'undefined' ? priority : '';
                style.setProperty(styleName, value, priority);
                return this;
            } else {
                // Get style property
                return style.getPropertyValue(styleName);
            }
        } else {
            // Get CSSStyleDeclaration
            return style;
        }
    };
})(jQuery);