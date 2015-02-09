var currentHash = null;

$(function() {
    jsFireHashChanged(true);
    $(window).on('hashchange', function() {
        jsFireHashChanged();
    });
});

function jsFireHashChanged(alwaysFire) {
    var hash = window.location.hash;
    if (hash != currentHash || alwaysFire) {
        var body = $('body');
        realtime.send({
            type: 'hashChanged',
            hash: hash
        });
        currentHash = hash;
    }
}

function setHash(newValue) {
    currentHash = '#' + newValue;
    if ('pushState' in history) {
        history.pushState(null, null, currentHash);
    } else {
        window.location.hash = currentHash;
    }
}