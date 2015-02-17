var EaselJS = {
    items: {},
    stage: function(id) {
        var s = EaselJS.items[id];
        if (s == null) {
            s = new createjs.Stage(id);
            EaselJS.items[id] = s;
        }
        return s;
    },
    shape: function(id, stageId) {
        var s = EaselJS.items[id];
        if (s == null) {
            s = new createjs.Shape();
            EaselJS.items[id] = s;

            var stage = EaselJS.stage(stageId);
            stage.addChild(s);
        }
        return s;
    }
};