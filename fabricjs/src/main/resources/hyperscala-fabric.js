var FabricJS = {
    canvas: {},
    object: {},
    filters: {},
    init: function(canvasId, canvas) {
        FabricJS.canvas[canvasId] = canvas;
        FabricJS.canvas[canvas] = canvasId;
    },
    add: function(canvasId, objectId, object) {
        var canvas = FabricJS.canvas[canvasId];
        FabricJS.object[objectId] = object;
        FabricJS.object[object] = objectId;
        canvas.add(object);
        // TODO: listen to changes and propagate back to server for non-static entries
        return object;
    },
    set: function(canvasId, objectId, key, value) {
        var canvas = FabricJS.canvas[canvasId];
        if (objectId != null) {
            var f = function() {
                var obj = FabricJS.object[objectId];
                if (obj != null) {
                    obj.set(key, value);
                } else {
                    setTimeout(f, 10);
                }
            };
            f();
        } else {
            canvas[key] = value;
        }
        canvas.renderAll();
    },
    canvasEvent: function(canvasId, eventName, handler) {
        var canvas = FabricJS.canvas[canvasId];
        if (canvas.__eventListeners != null) {              // Clear existing listeners
            canvas.__eventListeners[eventName] = [];
        }
        canvas.on(eventName, handler);
    },
    objectEvent: function(objectId, eventName, handler) {
        var object = FabricJS.object[objectId];
        if (object.__eventListeners != null) {              // Clear existing listeners
            object.__eventListeners[eventName] = [];
        }
        object.on(eventName, handler);
    },
    remove: function(canvasId, objectId) {
        var canvas = FabricJS.canvas[canvasId];
        var object = FabricJS.object[objectId];
        canvas.remove(object);
        FabricJS.object[objectId] = null;
    },
    animate: function(canvasId, objectId, property, adjust, options) {
        var canvas = FabricJS.canvas[canvasId];
        var object = FabricJS.object[objectId];
        options.onChange = canvas.renderAll.bind(canvas);
        object.animate(property, adjust, options);
    },
    addFilter: function(image, filterId, filter) {
        FabricJS.filters[filterId] = filter;
        image.filters.push(filter);
    },
    removeFilter: function(image, filterId) {
        var filter = FabricJS.filters[filterId];
        image.filters.splice(image.filters.indexOf(filter), 1);
    },
    canvasEventToServer: function(tagId, canvasId, type, options) {
        console.log('Sending: ' + type);
        realtime.send({
            type: type,
            id: tagId,
            canvasId: canvasId,
            objectId: FabricJS.object[options.target]
        });
    }
};