var FabricJS = {
    canvas: {},
    object: {},
    filters: {},
    add: function(canvasId, objectId, object) {
        var canvas = FabricJS.canvas[canvasId];
        FabricJS.object[objectId] = object;
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
    event: function(objectId, eventName, handler) {
        var object = FabricJS.object[objectId];
        //object.__eventListeners[eventName] = [];        // Clear existing listeners
        console.log('Object: ' + object + ', Listeners: ' + object.__eventListeners);
        console.log('Event: ' + eventName + ' - ' + handler);
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
    }
};