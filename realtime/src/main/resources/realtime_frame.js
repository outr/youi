HyperscalaConnect = (function() {
    var handlers = {};

    var send = function(event, data) {
        if (event != 'init') {                  // Ignore init
            data.parentId = iframeId;
            parent.HyperscalaConnect.send(event, data);
        }
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

    var lookupIframeId = function() {
        var w = document.defaultView || document.parentWindow;
        var frames = w.parent.document.getElementsByTagName('iframe');
        for (var i = 0; i < frames.length; i++) {
            var frame = frames[i];
            try {
                var d = frame.contentDocument || frame.contentWindow.document;
                if (d == document) {
                    return frame.getAttribute('id');
                }
            } catch(e) {
                // Ignore exceptions
            }
        }
        return null;
    };
    var iframeId = lookupIframeId();

    return {
        send: send,
        on: on
    };
})();