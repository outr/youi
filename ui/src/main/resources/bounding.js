var bounding = {
    entries: {},
    monitor: function(selector, frequency, selectorFunction) {
        bounding.remove(selector);         // Make sure this id isn't already being monitored

        var entry = {
            frequency: frequency
        };
        bounding.entries[selector] = bounding;
        entry.intervalId = setInterval(function() {
            var selection;
            if (selectorFunction == null) {
                selection = $(selector);
            } else {
                selection = selectorFunction();
            }
            bounding.check(selector, selection);
        }, frequency);
    },
    check: function(selector, selection) {
        var selectorEntry = bounding.entries[selector];
        if (selection.length > 0) {
            selection.each(function() {     // Iterate over each entry
                var element = $(this);
                var id = element.attr('id');
                var entry = selectorEntry[id];
                if (entry == null) {
                    entry = {};
                    selectorEntry[id] = entry;
                }

                var localX = parseInt(element.css('left'));
                if (isNaN(localX)) {
                    localX = 0;
                }
                var localY = parseInt(element.css('top'));
                if (isNaN(localY)) {
                    localY = 0;
                }
                var absoluteX = element.offset().left;
                var absoluteY = element.offset().top;
                var width = element.outerWidth();
                var height = element.outerHeight();

                var json = {};
                if (entry.localX != localX) {
                    json.localX = localX;
                }
                if (entry.localY != localY) {
                    json.localY = localY;
                }
                if (entry.absoluteX != absoluteX) {
                    json.absoluteX = absoluteX;
                }
                if (entry.absoluteY != absoluteY) {
                    json.absoluteY = absoluteY;
                }
                if (entry.width != width) {
                    json.width = width;
                }
                if (entry.height != height) {
                    json.height = height;
                }
                if (Object.keys(json).length > 0) {
                    json.elementId = id;

//                    console.log('Sending: ' + JSON.stringify(json));
                    realtimeSend(null, 'bounding', json);

                    entry.localX = localX;
                    entry.localY = localY;
                    entry.absoluteX = absoluteX;
                    entry.absoluteY = absoluteY;
                    entry.width = width;
                    entry.height = height;
                }
            });
        } else {
//            console.log('Selector ' + selector + ' has no elements.');
//            monitor.remove(id);
        }
    },
    remove: function(id) {
        var entry = bounding.entries[id];
        if (entry != null) {
            var intervalId = entry.intervalId;
            if (intervalId != null) {
                clearInterval(intervalId);
            }
            bounding.entries[id] = null;
        }
    }
};