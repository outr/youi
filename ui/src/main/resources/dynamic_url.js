$(function() {
    jsFireHashChanged();
    $(window).on('hashchange', function() {
        jsFireHashChanged();
    });
});

function jsFireHashChanged() {
    var hash = window.location.hash;
    var body = $('body');
    jsFire(body, 'hashChanged', {
        hash: hash
    });
}