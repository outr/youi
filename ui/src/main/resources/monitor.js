var monitor = {
    entries: {},
    createMonitor: function(id, frequency, evaluator) {
        var entry = {
            evaluator: evaluator
        };
        monitor.entries[id] = entry;
        entry.intervalId = setInterval(function() {
            monitor.checkMonitor(id);
        }, frequency);
    },
    checkMonitor: function(id) {
        var entry = monitor.entries[id];
        var result = entry.evaluator();
        if (result != entry.lastValue) {
            realtime.send({
                monitorId: id,
                type: 'monitor',
                value: result
            });
            entry.lastValue = result;
        }
    },
    removeMonitor: function(id) {
        var entry = monitor.entries[id];
        if (entry != null) {
            var intervalId = entry.intervalId;
            if (intervalId != null) {
                clearInterval(intervalId);
            }
            monitor.entries[id] = null;
        }
    }
};
window.monitor = monitor;