var FabricJS = {
    canvas: {},
    object: {},
    add: function(canvasId, objectId, object) {
        var canvas = FabricJS.canvas[canvasId];
        FabricJS.object[objectId] = object;
        canvas.add(object);
        return object;
    }
};