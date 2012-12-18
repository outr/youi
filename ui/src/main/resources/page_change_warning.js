var pageChangeWarning = null;

var pageChangeFunction = function() {
    return pageChangeWarning;
};

function setPageChangeWarning(message) {
    pageChangeWarning = message;
    if (message == null) {
        window.onbeforeunload = null;
    } else {
        window.onbeforeunload = pageChangeFunction;
    }
}