var resizeFunction = function() {
    var $window = $(window);
    $window.trigger('windowSized', [$window.width(), $window.height()]);
};

$(window).resize(resizeFunction);
$(document).ready(resizeFunction);