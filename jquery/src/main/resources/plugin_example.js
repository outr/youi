(function($) {
    var dataKey = 'history_settings';
    var methods = {
        init: function(options) {
            var settings = $.extend({
                color: 'green',
                backgroundColor: 'blue'
            }, options);
            return this.each(function() {
                var thisElement = this;
                var $this = $(this);
                $this.data(dataKey, settings);
                $.each(settings, function(key, value) {
                    methods.updated.apply(thisElement, [key, value]);
                });
            });
        },
        option: function(key, value) {
            if (value) {
                this.each(function() {
                    var $this = $(this);
                    var data = $this.data(dataKey);
                    data[key] = value;
                    methods.updated.apply(this, [key, value]);
                });
            }
            if (this.length > 0) {
                return this.first().data(dataKey)[key];
            }
            return null;
        },
        updated: function(key, value) {
            var $this = $(this);
            if (key == 'color') {
                $this.css('color', value);
            } else if (key == 'backgroundColor') {
                $this.css('background-color', value);
            }
        }
    };

    $.fn.history = function(method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.history');
            return this;
        }
    };
})(jQuery);