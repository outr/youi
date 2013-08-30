function initEditableContent(id) {
    var editable = $('#' + id);
    editable.data('editable_content_executing', false);

    editable.on('focus', function() {
        if (!editable.data('editable_content_executing')) {
            $(this).data('saved_html', $(this).html());
        }
    });
    editable.on('blur', function() {
        if (!editable.data('editable_content_executing')) {
            var html = $(this).data('saved_html');
            var updated = $(this).html();
            if (html != updated) {
                communicator.send('htmlChanged', id, {
                    html: updated
                });
                $(this).data('saved_html', updated);
            }
        }
    });
}

function editableContentExecCommand(id, command, value) {
    var editable = $('#' + id);
    editable.data('editable_content_executing', true);
    document.execCommand(command, false, value);
    editable.data('editable_content_executing', false);
}