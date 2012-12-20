var resizeFunction = function() {
    $(window).trigger('windowSized', [$(window).width(), $(window).height()]);
//    console.log('Window size: ' + $(window).width() + 'x' + $(window).height());
};

$(window).resize(resizeFunction);
$(document).ready(resizeFunction);

