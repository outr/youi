var editableContentMonitor = null;
var editableContent = null;
var editableContentStored = null;
var editableSelectionStored = null;
var editableState = null;

/**
 * Initializes the editable content and assigns listeners.
 *
 * @param id the id of the element to initialize
 */
function initEditableContent(id) {
    var editable = $('#' + id);
    editable.on('focus', function() {
        editableContent = editable;
        editableContentStored = editableContent.html();
        editableSelectionStored = null;
        editableState = {};
        window.clearInterval(editableContentMonitor);
        editableContentMonitor = window.setInterval(monitorEditableContent, 100);
    });
    editable.on('blur', function() {
        editableContentMonitor = window.clearInterval(editableContentMonitor);
        editableContent = null;
    });
}

/**
 * Called on an interval to check the selection and content of the editable content.
 */
function monitorEditableContent() {
    var realtimeContent = editableContent.data('realtime-content');
    var realtimeSelection = editableContent.data('realtime-selection');

    // Check content changed
    if (realtimeContent) {
        validateEditableContent();
    }

    // Check selection changed
    if (realtimeSelection) {
        validateEditableSelection();
    }
}

/**
 * Validates the content against the stored content to check to see if the content has changed.
 *
 * This is called by monitorEditableContent if realtimeContent is true and during blur.
 *
 * If the content has been changed an htmlChanged event will be sent to the server.
 */
function validateEditableContent() {
    var current = editableContent.html();
    if (editableContentStored != current) {
        communicator.send('htmlChanged', editableContent.attr('id'), {
            html: updated
        });
        editableContentStored = current;
    }
}

/**
 * Validates the selection against the stored selection to check to see if the selection has changed.
 *
 * This is called by monitorEditableContent if realtimeSelection is true.
 *
 * If the content has been changed a selectionChanged event will be sent to the server.
 */
function validateEditableSelection() {
    var selection = window.getSelection();
    var range = selection.getRangeAt(0);
    var startOffset = actualIndex(range.startContainer, range.startOffset);
    var endOffset = actualIndex(range.endContainer, range.endOffset);
    var current = startOffset + 'x' + endOffset;
    if (editableSelectionStored != current) {
        var obj = {};
        verifyContent(obj, 'text', selection.toString());
        verifyContent(obj, 'html', getSelectionHtml());
        verifyContent(obj, 'startOffset', startOffset);
        verifyContent(obj, 'endOffset', endOffset);
        verifyContent(obj, 'backColor', document.queryCommandValue('backColor'));
        verifyContent(obj, 'bold', document.queryCommandState('bold'));
        verifyContent(obj, 'createLink', getLinked(range.startContainer));
        verifyContent(obj, 'fontName', document.queryCommandValue('fontName'));
        verifyContent(obj, 'fontSize', document.queryCommandValue('fontSize'));
        verifyContent(obj, 'foreColor', document.queryCommandValue('foreColor'));
        verifyContent(obj, 'hiliteColor', document.queryCommandValue('hiliteColor'));
        verifyContent(obj, 'italic', document.queryCommandState('italic'));
        verifyContent(obj, 'justifyCenter', document.queryCommandState('justifyCenter'));
        verifyContent(obj, 'justifyFull', document.queryCommandState('justifyFull'));
        verifyContent(obj, 'justifyLeft', document.queryCommandState('justifyleft'));
        verifyContent(obj, 'justifyRight', document.queryCommandState('justifyRight'));
        verifyContent(obj, 'strikeThrough', document.queryCommandState('strikeThrough'));
        verifyContent(obj, 'subscript', document.queryCommandState('subscript'));
        verifyContent(obj, 'superscript', document.queryCommandState('superscript'));
        verifyContent(obj, 'underline', document.queryCommandState('underline'));

        communicator.send('selectionChanged', editableContent.attr('id'), obj);
        editableSelectionStored = current;
    }
}

function verifyContent(obj, key, value) {
    if (editableState[key] != value) {
        obj[key] = value;
        editableState[key] = value;
    }
}

function getLinked(element) {
    if (element.nodeName == 'A') {
        return element.href;
    } else if (element.parentNode != null && element.parentNode != editableContent.get(0)) {
        return getLinked(element.parentNode)
    }
    return null;
}

function actualIndex(element, offset) {
    var previous = element.previousSibling;
    if (previous != null) {
        if (previous.textContent != null) {
            offset += previous.textContent.length;
        }
        return actualIndex(previous, offset);
    } else if (element.parentNode != editableContent.get(0)) {
        return actualIndex(element.parentNode, offset);
    } else {
        return offset;
    }
}

/**
 * Executes a command on the current editable content.
 *
 * @param id
 * @param command
 * @param value
 */
function editableContentExecCommand(id, command, value) {
    var editable = $('#' + id);
    document.execCommand(command, false, value);
}

/**
 * Cross-browser method of getting the editable content's selection HTML.
 *
 * @returns {string}
 */
function getSelectionHtml() {
    var html = "";
    if (typeof window.getSelection != "undefined") {
        var sel = window.getSelection();
        if (sel.rangeCount) {
            var container = document.createElement("div");
            for (var i = 0, len = sel.rangeCount; i < len; ++i) {
                container.appendChild(sel.getRangeAt(i).cloneContents());
            }
            html = container.innerHTML;
        }
    } else if (typeof document.selection != "undefined") {
        if (document.selection.type == "Text") {
            html = document.selection.createRange().htmlText;
        }
    }
    return html;
}