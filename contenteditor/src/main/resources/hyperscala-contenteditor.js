var ContentEditor = {
    instances: {},
    listeners: {},
    byId: function(id) {
        var instance = this.instances[id];
        if (instance == null) {
            instance = new ContentEditorInstance(id);
            this.instances[id] = instance;
        }
        return instance;
    },
    exists: function(id) {
        return this.instances[id] != null;
    },
    on: function(key, f) {
        if (this.listeners[key] == null) {
            this.listeners[key] = [];
        }
        this.listeners[key].push(f);
    },
    fire: function(key, evt) {
        if (this.listeners[key]) {
            for (var i = 0; i < this.listeners[key].length; i++) {
                this.listeners[key][i](evt);
            }
        }
    },
    lastStyle: null,
    check: function() {
        var activeId = null;
        if (document.activeElement != null) {
            activeId = $(document.activeElement).attr('id');
        }
        if (this.instances[activeId]) {       // Only fire change if editable is focused
            var currentStyle = this.style2JSON(contentEditor.selectionInfo().scopedStyle);
            if (JSON.stringify(currentStyle) != JSON.stringify(this.lastStyle)) {
                this.fire('styleChanged', {
                    instance: this.instances[document.activeElement],
                    style: currentStyle,
                    previous: this.lastStyle
                });
                this.lastStyle = currentStyle;
            }
        }
    },
    bind: function(id, key, doc) {
        if (doc == null) doc = document;
        var element = doc.jQuery('#' + id);
        if (element.size() == 0) {
            realtime.error('ContentEditorInstance.bind: Unable to find element by id ' + id + ' (style: ' + key + ')');
        } else {
            element.change(function() {
                console.log('Value changed: ' + element.val());
                var activeId = null;
                if (document.activeElement != null) {
                    activeId = $(document.activeElement).attr('id');
                }
                if (ContentEditor.exists(activeId)) {
                    ContentEditor.byId(activeId).set(key, element.val());
                }
            });
            ContentEditor.on('styleChanged', function(evt) {
                element.val(evt.style[key]);
            });
        }
    },
    style2JSON: function(style) {
        var json = {};
        for (var i = 0; i < style.length; i++) {
            json[style[i]] = style[style[i]];
        }
        return json;
    }
};

$(document).on('selectionchange', function() {
    ContentEditor.check();
});

var ContentEditorInstance = function(id) {
    this.element = document.getElementById(id);
    if (this.element == null) {
        throw 'Element not found by id: ' + id;
    }
    this.formatters = {};
};

ContentEditorInstance.prototype.focused = function() {
    return this.element == document.activeElement;
};

ContentEditorInstance.prototype.stylize = function(key, value) {
    if (this.focused()) {
        var formatter = this.formatters[key];
        if (formatter == null) {
            formatter = contentEditor.createClassWrapper('stylized-' + key);
            this.formatters[key] = formatter;
        }
        formatter.undo();
        formatter.style[key] = value;
        formatter.apply();
        ContentEditor.check();
    }
};

ContentEditorInstance.prototype.style = function(key) {
    return contentEditor.selectionInfo().scopedStyle[key];
};

ContentEditorInstance.prototype.hasStyle = function(key, value) {
    return this.style(key) == value;
};

ContentEditorInstance.prototype.toggle = function(key, value, reverse) {
    if (reverse == null) {
        reverse = '';
    }
    if (this.hasStyle(key, value)) {
        this.stylize(key, reverse);
    } else {
        this.stylize(key, value);
    }
};

ContentEditorInstance.prototype.set = function(key, value) {
    this.stylize(key, value);
};

ContentEditorInstance.prototype.insert = function(tagName, details) {
    if (this.focused()) {
        var formatter = this.formatters[tagName];
        if (formatter == null) {
            formatter = contentEditor.createHtmlWrapper(tagName);
            this.formatters[tagName] = formatter;
        }
        formatter.insert(details);
        ContentEditor.check();
    }
};