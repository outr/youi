var FabricJS = {
    canvas: {},
    object: {},
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
            var obj = FabricJS.object[objectId];
            obj.set(key, value);
        } else {
            canvas[key] = value;
        }
        canvas.renderAll();
    },
    remove: function(canvasId, objectId) {
        var canvas = FabricJS.canvas[canvasId];
        var object = FabricJS.object[objectId];
        canvas.remove(object);
        FabricJS.object[objectId] = null;
    }
};