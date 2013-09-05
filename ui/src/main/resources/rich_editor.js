window.richEditors = {};

function createRichEditor(id, updateFrequency) {
    var intervalId;

    CKEDITOR.replace(document.getElementById(id), {
        on: {
            configLoaded: function() {
//                this.config.removePlugins = 'toolbar, elementspath, resize, contextmenu, liststyle, tabletools';
                this.config.disableNativeSpellChecker = false;
                this.config.allowedContent = true;
            },
            focus: function() {
                if (updateFrequency > 0) {
                    intervalId = setInterval(function() {
                        validateRichEditor(id);
                    }, updateFrequency);
                }
            },
            blur: function() {
                if (updateFrequency > 0) {
                    clearInterval(intervalId);
                }
                validateRichEditor(id);
            },
            instanceReady: function() {
                window.richEditors[id] = this;
            }
        }
    });
    setTimeout(function() {

    }, updateFrequency);
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
    var editor = window.richEditors[id];
    if (editor != null) {                   // Found the editor
        var editable = editor.editable();
        editable.setHtml(value);
        editor.resetDirty();
    } else {                                // Wait until the editor instance has initialized
        setTimeout(function() {
            updateRichEditor(id, value);
        }, 100);
    }
}