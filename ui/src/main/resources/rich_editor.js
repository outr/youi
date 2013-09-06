window.richEditors = {};
window.richEditorOptions = {};

var intervalId;

function createRichEditor(id) {
    CKEDITOR.replace(document.getElementById(id), {
        on: {
            configLoaded: function() {
                this.config.disableNativeSpellChecker = false;
            },
            focus: function() {
                var options = window.richEditorOptions[id];
                options.focused = true;
                updateRichEditorUpdateFrequency(id, options);
            },
            blur: function() {
                var options = window.richEditorOptions[id];
                options.focused = false;
            },
            instanceReady: function() {
                window.richEditors[id] = this;
                var options = {
                    updateFrequency: 1000,
                    showToolbar: false,
                    showPath: true,
                    showResizer: true
                };
                window.richEditorOptions[id] = options;
                updateRichEditorControlsVisibility(id, options);
            }
        }
    });
}

function richEditorOption(id, key, value) {
    var options = window.richEditorOptions[id];
    if (options == null) {      // Wait for editor to finish initializing
        setTimeout(function() {
            richEditorOption(id, key, value);
        }, 100);
    } else {
        options[key] = value;
        if (key == 'updateFrequency') {
            updateRichEditorUpdateFrequency(id, options);
        } else if (key == 'readOnly') {
            window.richEditors[id].setReadOnly(value);
        } else if (key == 'html') {
            updateRichEditor(id, value);
        } else if (key == 'showToolbar') {
            updateRichEditorControlsVisibility(id, options);
        } else if (key == 'showPath') {
            updateRichEditorControlsVisibility(id, options);
        } else if (key == 'showResizer') {
            updateRichEditorControlsVisibility(id, options);
        } else {
            console.log('Unknown rich editor option: ' + key + '=' + value);
        }
    }
}

function updateRichEditorControlsVisibility(id, options) {
    var top = $('#cke_' + id + ' .cke_top');
    var bottom = $('#cke_' + id + ' .cke_bottom');
    var path = $('#cke_' + id + ' .cke_path');
    var resizer = $('#cke_' + id + ' .cke_resizer');

    if (options.showToolbar) {
        top.show();
    } else {
        top.hide();
    }
    if (options.showPath || options.showResizer) {
        bottom.show();
        if (options.showPath) {
            path.show();
        } else {
            path.hide();
        }
        if (options.showResizer) {
            resizer.show();
        } else {
            resizer.hide();
        }
    } else {
        bottom.hide();
    }
}

function updateRichEditorUpdateFrequency(id, options) {
    if (options.focused && options.updateFrequency > 0) {
        options.intervalId = setInterval(function() {
            validateRichEditor(id);
        }, options.updateFrequency);
    } else {
        options.intervalId = clearInterval(options.intervalId);
        validateRichEditor(id);
    }
}

function validateRichEditor(id) {
    var editor = CKEDITOR.instances[id];
    if (editor.checkDirty()) {
        var value = editor.getData();
        communicator.send('editorChanged', id, {
            value: value
        });
        editor.resetDirty();
    }
}

function updateRichEditor(id, value) {
    invokeAfterRichEditorInit(id, function() {      // Make sure the editor is initialized before we call this
        var editor = window.richEditors[id];
        var editable = editor.editable();
        editable.setHtml(value);
        editor.resetDirty();
    });
}

function richEditorExecCommand(id, command, data) {
    var editor = window.richEditors[id];
    if (editor != null) {
        editor.execCommand(command, data);
    }
}

function richEditorAttachStyleStateChange(id, element, overrides, f) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        var style = new CKEDITOR.style({ element: element, overrides: overrides });
        var lastState = null;
        editor.attachStyleStateChange(style, function(state) {
            var newState = state == CKEDITOR.TRISTATE_ON;
            if (newState != lastState) {
                f(newState);
                lastState = newState;
            }
        });
    });
}

function invokeAfterRichEditorInit(id, f) {
    var editor = window.richEditors[id];
    if (editor != null) {
        f();
    } else {
        setTimeout(function() {
            invokeAfterRichEditorInit(id, f);
        }, 100);
    }
}