var modalComponent = null;
var modalData;
var modalLeft;
var modalTop;
var modalRight;
var modalBottom;
var modalComponentWindow;

$(document).ready(function() {
    var body = $('body');
    modalLeft = $('#modal_left');
    modalTop = $('#modal_top');
    modalRight = $('#modal_right');
    modalBottom = $('#modal_bottom');
    modalComponentWindow = $(window);
    modalData = {};
    updateModal();
    setInterval(updateModal, 500);
});

function updateModal() {
    if (modalComponent == null) {
        modalLeft.css('display', 'none');
        modalTop.css('display', 'none');
        modalRight.css('display', 'none');
        modalBottom.css('display', 'none');
        modalData = {};
    } else {
        var fracs = modalComponent.fracs();
        var vp = fracs.rects.viewport;
        if (JSON.stringify(modalData) != JSON.stringify(vp)) {
            var windowWidth = modalComponentWindow.width();
            var windowHeight = modalComponentWindow.height();
            console.log(JSON.stringify(fracs));
//            console.log('old: ' + JSON.stringify(modalData));
//            console.log('new: ' + JSON.stringify(vp) + ' - ' + windowWidth + 'x' + windowHeight);
            modalData = vp;
            modalLeft.css({
                left: 0,
                top: 0,
                width: vp.left,
                height: windowHeight,
                display: 'block'
            });
            modalTop.css({
                left: vp.left,
                top: 0,
                width: vp.width,
                height: vp.top,
                display: 'block'
            });
            modalRight.css({
                left: vp.right,
                top: 0,
                width: windowWidth - vp.right,
                height: windowHeight,
                display: 'block'
            });
            modalBottom.css({
                left: vp.left,
                top: vp.bottom,
                width: vp.width,
                height: windowHeight - vp.bottom,
                display: 'block'
            });
        }
    }
}