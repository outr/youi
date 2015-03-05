var Screen = {
    url: document.location.href,
    activate: function(uri, replace) {
        if (replace) {
            history.replaceState({}, '', uri);
        } else {
            history.pushState({}, '', uri);
        }
        Screen.check();
    },
    check: function() {
        var url = document.location.href;
        if (url != Screen.url) {
            realtime.send({
                type: 'urlChange',
                url: url
            });

            Screen.url = url;
        }
    }
};

$(window).on('popstate', function() {
    Screen.check();
});