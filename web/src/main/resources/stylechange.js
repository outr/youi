(function() {
    var orig = $.fn.css;
    $.fn.css = function() {
        orig.apply(this, arguments);
        if (!applyingChanges) {
            this.trigger('stylechange', {
                'propertyName': arguments[0],
                'propertyValue': arguments[1]
            });
        }
    }
})();