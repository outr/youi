var debug = false;

if (!Date.now) {
  Date.now = function now() {
    return new Date().getTime();
  };
}

HyperscalaConnect.on('eval', function(data) {
    try {
        realtimeEvaluate(data, debug);
    } catch(err) {
        log('Failed to evaluate instruction: ' + JSON.stringify(data) + ' - ' + err);
    }
});

HyperscalaConnect.on('jquery.call', function(data) {
    try {
        var selector = data['selector'];
        var call = data['call'];
        var args = data['args'];
        log('jquery.call: ' + selector + ', ' + call + ', ' + args);
        $(selector)[call](args);
    } catch(err) {
        log('Failed to evaluate instruction: ' + JSON.stringify(data) + ' - ' + err);
    }
});

realtimeSend(null, 'init', {});

/**
 * Add this call to a JavaScript event to fire the event down to the server.
 *
 * @param event
 * @param data
 * @param confirmation
 * @param preventDefault
 * @param fireChange
 * @param delay
 */
function realtimeEvent(event, data, confirmation, preventDefault, fireChange, delay) {
    try {
        if (event.srcElement) event.target = event.srcElement;

        var element = $(event.currentTarget);
        var id = element.attr('id');
        if (id == null) {
            element = $(event.target);
            id = element.attr('id');
        }

        if (id != null) {
            var eventType = event.type;
            var content = {
                id: id,
                target: $(event.target).attr('id'),
                data: data
            };

            // Update the content with specific data
            if ('keydown, keypress, keyup'.indexOf(eventType) != -1) {
                realtimeUpdateKeyEvent(event, content);
            } else if (eventType == 'change') {
                content.value = realtimeChangeEventValue(element);
            }

            var f = function() {
                if (fireChange) {
                    realtimeSend(id, 'change', jQuery.extend(content, {
                        value: realtimeChangeEventValue(element)
                    }));
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
        } else {
            log('realtimeEvent: element id is null, so not firing event.');
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

var realtimeCache = {};
var realtimeCaching = true;

function realtimeEvaluate(json, debug) {
    window.content = json['content'];
    var instruction = json['instruction'];
    var selector = json['selector'];
    var delay = json['delay'];
    if (json['parentFrameId'] != null) {
        var parentFrameId = json['parentFrameId'];
        var parentFrame = $('#' + parentFrameId);
        json['parentFrameId'] = null;
        var cache = realtimeCache[parentFrameId];
        if (parentFrame.length > 0 && parentFrame.get(0).contentWindow.realtimeEvaluate) {
            if (cache != null) {        // Process the cache before we do anything else (if there is anything in it)
                cache.evaluate();
            }
            parentFrame.get(0).contentWindow.realtimeEvaluate(json, debug);
        } else if (realtimeCaching) {
            if (cache == null) {
                cache = {
                    backlog: []
                };
                cache.evaluate = function() {
                    var parentFrame = $('#' + parentFrameId);
                    if (parentFrame.length > 0 && parentFrame.get(0).contentWindow.realtimeEvaluate) {
//                        console.log('Processing backlog: ' + cache.backlog.length);
                        for (var i = 0; i < cache.backlog.length; i++) {
                            parentFrame.get(0).contentWindow.realtimeEvaluate(cache.backlog[i], debug);
                        }
                        realtimeCache[parentFrameId] = null;
                        clearInterval(cache.timer);
                        return true;
                    } else {
                        return false;
                    }
                };
                realtimeCache[parentFrameId] = cache;
                cache.timer = setInterval(function() {
                    cache.evaluate();
                }, 100);
            }
            cache.backlog.push(json);
//            console.log('Unable to find frame by id: ' + parentFrameId + ' for ' + JSON.stringify(json));
        }
    } else {
        if (delay > 0) {                                // Handle delay if specified
            json['delay'] = 0;
            setTimeout(function () {
                realtimeEvaluate(json, debug);
            }, delay);
        } else if (selector != null) {
            if (eval('$(' + selector + ').length') == 0) {              // Selector returned empty, wait a few milliseconds and check again
                if (debug) {
                    log('Selector: ' + selector + ' returned empty...waiting...');
                }
                setTimeout(function () {
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
                globalEval(instruction);
            } catch (err) {
                log('Error occurred (' + err.message + ') while attempting to evaluate instruction: [' + instruction + '] with content: [' + content + ']. Stack: ' + err.stack);
            }
        }
    }
}

function globalEval(src) {
    if (window.execScript) {        // eval in global scope for IE
        window.execScript(src);
    } else {                        // other browsers
        eval.call(null, src);
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
    var current = Date.now();
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
        group.lastSend = Date.now();
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