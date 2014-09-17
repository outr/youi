window.richEditors = {};
window.richEditorOptions = {};

CKEDITOR.disableAutoInline = true;

function createRichEditor(id, inline) {
    var element = document.getElementById(id);
    if (element == null) {
        setTimeout(function () {
            createRichEditor(id, inline);
        }, 100);
    } else {
        var configuration = {
            removePlugins: 'undo',
            title: false,
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
                },
                selectionChange: function(ev) {
                    var options = window.richEditorOptions[id];
                    options.selectionPath = ev.data.path;
                }
            },
            fontSize_style: {
                element: 'span',
                styles: {
                    'font-size': '12pt'
                },
                overrides: {
                    element: 'font',
                    attributes: {
                        size: null
                    }
                }
            },
            coreStyles_bold: {
                element: 'span',
                attributes: {
                    style: 'font-weight: bold'
                },
                overrides: ['strong', 'b']
            },
            coreStyles_italic: {
                element: 'span',
                attributes: {
                    style: 'font-style: italic'
                },
                overrides: ['em', 'i']
            }
        };
        if (inline) {
            CKEDITOR.inline(element, configuration);
        } else {
            CKEDITOR.replace(element, configuration);
        }
    }
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
        } else if (key.indexOf('showToolbarButton') == 0) {
            updateRichEditorToolbarButton(id, key.substring(17), value)
        } else {
            console.log('Unknown rich editor option: ' + key + '=' + value);
        }
    }
}

function updateRichEditorToolbarButton(id, name, value) {
    var selector = $('#cke_' + id + ' .cke_button__' + name);
    if (value) {
        selector.show();
    } else {
        selector.hide();
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
        realtimeSend(id, 'editorChanged', {
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
        if (command == 'delete') {
            var range = editor.getSelection().getRanges()[0];
            range.deleteContents();
            range.select();
        } else {
            editor.execCommand(command, data);
        }
    }
}

function richEditorInsert(id, mode, content) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        editor.insertHtml(content, mode);
    });
}

function richEditorAttachStyleStateChange(id, style, f) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        var lastState = null;
        editor.attachStyleStateChange(style, function(state) {
            var newState = state == CKEDITOR.TRISTATE_ON;
            if (newState != lastState) {
                try {
                    f(newState);
                    lastState = newState;
                } catch(err) {
                    console.log('Error occurred in richEditorAttachStyleStateChange while calling function: ' + err.message);
                }
            }
        });
    });
}

function richEditorAttachStyleValueChange(id, cssName, f) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        var lastValue = null;
        editor.on('selectionChange', function() {
            var newValue = richEditorGetStyle(id, cssName);
            if (newValue != lastValue) {
                try {
                    f(newValue);
                    lastValue = newValue;
                } catch(err) {
                    console.log('Error occurred in richEditorAttachStyleValueChange while calling function: ' + err.message + ' (' + f + ')');
                }
            }
        });
    });
}

function richEditorGetStyle(id, cssName) {
    var editor = window.richEditors[id];
    var options = window.richEditorOptions[id];
    if (editor != null && options != null && options.selectionPath != null) {
        var elements = options.selectionPath.elements;
        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            var cssValue = element.getStyle(cssName);
            if (cssValue != '') {
                return cssValue;
            }
        }
    }
    return null;
}

function richEditorApplyStyle(id, style) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        editor['applyStyle'](style);
    });
}

function richEditorRemoveStyle(id, style) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        editor['removeStyle'](style);
    });
}

function richEditorToggleStyle(id, style) {
    invokeAfterRichEditorInit(id, function() {
        var editor = window.richEditors[id];
        if (richEditorCheckStyle(id, style)) {
            editor['removeStyle'](style);
        } else {
            editor['applyStyle'](style);
        }
    });
}

/**
 * Checks the current selection status of the applied style.
 *
 * @param id the editor id
 * @param style the style to match against
 * @returns {boolean}
 */
function richEditorCheckStyle(id, style) {
    var editor = window.richEditors[id];
    var options = window.richEditorOptions[id];
    if (editor != null && options != null && options.selectionPath != null) {
        var elements = options.selectionPath.elements;
        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            if (style.checkElementMatch(element, true)) {
                return true;
            }
        }
    }
    return false;
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