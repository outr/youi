$(document).ready(function() {
    var vKey = 86;
    var cKey = 67;
    var xKey = 88;
    var mouseX = 0;
    var mouseY = 0;

    $(document).on('mousemove', function(evt) {
        mouseX = evt.pageX ? evt.pageX : evt.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
        mouseY = evt.pageY ? evt.pageY : evt.clientY + document.documentElement.scrollTop + document.body.scrollTop;
    });
    $(document).on('keydown', '.use-clipboard', function(evt) {
        if (evt.ctrlKey || evt.metaKey) {
            if (evt.keyCode == vKey) {
                fireClipboardEvent('paste', evt);
                return false;
            } else if (evt.keyCode == cKey) {
                fireClipboardEvent('copy', evt);
                return false;
            } else if (evt.keyCode == xKey) {
                fireClipboardEvent('cut', evt);
                return false;
            }
        }
        return true;
    });

    function fireClipboardEvent(eventType, evt) {
        var current = $(evt.target);
        if (current.attr('id') == null) {
            current = $(evt.currentTarget);
        }
        rangy.getSelection().refresh();
        var selected = rangy.getSelection().toHtml();
        if (current.is('input') || current.is('textarea')) {
            selected = current.getSelection().text;
        }
        realtimeSend('clipboard_instance', eventType, {
            eventId: current.attr('id'),
            selected: selected,
            mouseX: mouseX,
            mouseY: mouseY
        });
    }
});