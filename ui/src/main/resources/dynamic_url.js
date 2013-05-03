var currentHash = null;

$(function() {
    jsFireHashChanged();
    $(window).on('hashchange', function() {
        jsFireHashChanged();
    });
});

function jsFireHashChanged() {
    var hash = window.location.hash;
    if (hash != currentHash) {
        var body = $('body');
        jsFire(body, 'hashChanged', {
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