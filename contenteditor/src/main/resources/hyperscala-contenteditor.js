var ContentEditor = {
    instances: {},
    byId: function(id) {
        var instance = this.instances[id];
        if (instance == null) {
            instance = new ContentEditorInstance(id);
            this.instances[id] = instance
        }
        return instance;
    }
};

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
    }
};

ContentEditorInstance.prototype.bind = function(id, key) {
    var element = document.getElementById(id);
    if (element == null) {
        realtime.error('ContentEditorInstance.bind: Unable to find element by id ' + id + ' (style: ' + key + ')');
    } else {
        var $element = $(element);
        $element.change(function() {
            console.log('Value changed: ' + $element.val());
        });
    }
};