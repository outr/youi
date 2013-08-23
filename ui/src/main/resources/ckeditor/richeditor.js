function createRichEditor(id, updateFrequency) {
    var intervalId;

    CKEDITOR.replace(document.getElementById(id), {
        on: {
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
    var editor = CKEDITOR.instances[id];
    var editable = editor.editable();
    editable.setHtml(value);
}