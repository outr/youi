var ScriptLoader = {
    loading: false,
    backLog: [],
    finishCallbacks: [],
    load: function(url, cache) {
        var entry = {
            url: url,
            cache: cache
        };
        ScriptLoader.backLog.push(entry);
        ScriptLoader.loadNext();
    },
    loadMultiple: function(urls, cache) {
        for (var i = 0; i < urls.length; i++) {
            ScriptLoader.load(urls[i], cache);
        }
    },
    loadNext: function() {
        if (!ScriptLoader.loading) {
            var entry = ScriptLoader.backLog.shift();
            if (entry != undefined) {
                ScriptLoader.loading = true;
                jQuery.ajax(entry).done(function (script, textStatus) {
                    realtime.log('Script loaded successfully.', entry);
                }).fail(function (jqxhr, settings, exception) {
                    realtime.error('Failed to asynchronously load script.', entry, exception);
                }).always(function() {
                    ScriptLoader.loading = false;
                    ScriptLoader.loadNext();
                });
            } else {
                for (var i = 0; i < ScriptLoader.finishCallbacks.length; i++) {
                    realtime.send({
                        type: 'scriptLoaded',
                        callId: ScriptLoader.finishCallbacks[i]
                    });
                }
                ScriptLoader.finishCallbacks = [];
            }
        }
    },
    onFinish: function(id) {
        if (!ScriptLoader.loading && ScriptLoader.backLog.length == 0) {            // Nothing queued
            realtime.send({
                id: id
            });
        } else {
            ScriptLoader.finishCallbacks.push(id);
        }
    }
};