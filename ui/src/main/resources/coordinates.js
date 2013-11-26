/**
 * Checks to see if the coordinates (local position, absolute position, or size) has changed since the last check. If it
 * has changed then data will be sent to the server to update the known coordinates.
 *
 * @param id the id to check coordinates for
 * @param batch true if changes should be batched or if the change data should send immediately
 */
function checkCoordinates(id, batch) {
    var element = $('#' + id);
    var oldLocalX = element.data('local-x');
    var oldLocalY = element.data('local-y');
    var oldAbsX = element.data('abs-x');
    var oldAbsY = element.data('abs-y');
    var oldWidth = element.data('width');
    var oldHeight = element.data('height');

    var localX = parseInt(element.css('left'));
    var localY = parseInt(element.css('top'));
    var absX = element.offset().left;
    var absY = element.offset().top;
    var width = element.width();
    var height = element.height();

    if (oldLocalX != localX || oldLocalY != localY || oldAbsX != absX || oldAbsY != absY || oldWidth != width || oldHeight != height) {
        var obj = {
            localX: localX,
            localY: localY,
            absX: absX,
            absY: absY,
            width: width,
            height: height
        };
        if (batch) {
            groupedSend('coordinates-' + id, 250, 'coordinates', id, obj);
        } else {
            realtimeSend(id, 'coordinates', obj);
        }

        // Update local data to only send on-change
        element.data('local-x', localX);
        element.data('local-y', localY);
        element.data('abs-x', absX);
        element.data('abs-y', absY);
        element.data('width', width);
        element.data('height', height);
    }
}