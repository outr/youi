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

    this.fmtBold = contentEditor.createClassWrapper("fmt-bold", "font-weight: bold;");
};

ContentEditorInstance.prototype.focused = function() {
    return this.element == document.activeElement;
};

ContentEditorInstance.prototype.toggleBold = function() {
    if (this.focused()) {
        this.fmtBold.toggle();
    }
};

/*
 bold: {
 is: function() {

 }
 }
 */