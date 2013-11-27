/**
 * Checks to see if the coordinates (local position, absolute position, or size) has changed since the last check. If it
 * has changed then data will be sent to the server to update the known coordinates.
 *
 * @param id the id to check coordinates for
 * @param batch true if changes should be batched or if the change data should send immediately
 */
function checkCoordinates(id, batch) {
    var element = $('#' + id);
    var oldX = element.data('x');
    var oldY = element.data('y');
    var oldWidth = element.data('width');
    var oldHeight = element.data('height');

    var x = parseInt(element.css('left'));
    var y = parseInt(element.css('top'));
    var width = element.outerWidth();
    var height = element.outerWidth();

    if (oldX != x || oldY != y || oldWidth != width || oldHeight != height) {
        var obj = {
            x: x,
            y: y,
            width: width,
            height: height
        };
        if (batch) {
            groupedSend('coordinates-' + id, 250, 'coordinates', id, obj);
        } else {
            realtimeSend(id, 'coordinates', obj);
        }

        // Update local data to only send on-change
        element.data('x', x);
        element.data('y', y);
        element.data('width', width);
        element.data('height', height);
    }
}