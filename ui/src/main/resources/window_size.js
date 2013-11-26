var resizeFunction = function() {
    $(window).trigger('windowSized', [$(window).width(), $(window).height()]);
};

$(window).resize(resizeFunction);
$(document).ready(resizeFunction);

