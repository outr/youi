var pageChangeWarning = null;

window.onbeforeunload = function() {
    return pageChangeWarning;
};

function setPageChangeWarning(message) {
    pageChangeWarning = message;
}