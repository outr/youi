(function($) {
    var dataKey = 'changeable_settings';
    var methods = {
        init: function(options) {
            var settings = $.extend({
                changing: [],
                frequency: 100,
                sendChanges: false
            }, options);
            return this.each(function() {
                var thisElement = this;
                var $this = $(this);
                var data = $this.data(dataKey);
                if (data != null) {     // Already initialized, so lets merge settings
                    settings = $.extend(data, options);
                }
                $this.data(dataKey, settings);
                $.each(settings, function(key, value) {
                    methods.updated.apply(thisElement, [key, value]);
                });
            });
        },
        option: function(key, value) {
            this.each(function() {
                var $this = $(this);
                var data = $this.data(dataKey);
                data[key] = value;
                methods.updated.apply(this, [key, value]);
            });
            if (this.length > 0) {
                return this.first().data(dataKey)[key];
            }
            return null;
        },
        updated: function(key, value) {
            var $this = $(this);
            var data = $this.data(dataKey);
            if (key == 'changing') {
                if (data['timerId'] == null && value.length > 0) {
                    methods.refreshInterval($this);     // Initialize the updates if it hasn't already been done
                } else if (data['timerId'] != null && value.length == 0) {
                    methods.cancelInterval($this);      // Stop updates if it is updating
                }
            } else if (key == 'frequency') {
                if (data['changing'].length == 0) {
                    methods.cancelInterval($this);          // Stop updates if nothing to change
                } else {
                    methods.refreshInterval($this);         // Refresh the interval if there are things to change
                }
            } else if (key == 'timerId') {
                // Ignore
            } else if (key == 'sendChanges') {
                // Ignore
            } else {
                console.log('Unknown option for changeable: ' + key);
            }
        },
        refreshInterval: function($this) {
            methods.cancelInterval($this);
            var data = $this.data(dataKey);
            data['timerId'] = setInterval(function() {
                methods.updateChangeable($this);
            }, data['frequency']);
        },
        cancelInterval: function($this) {
            var data = $this.data(dataKey);
            var previousId = data['timerId'];
            if (previousId != null) {
                data['timerId'] = clearInterval(previousId);
            }
        },
        updateChangeable: function($this) {
            var data = $this.data(dataKey);
            var changing = data['changing'];
            var changes = {
                attributes: {
                    get: function(name) {
                        if (changes.attributes[name]) {
                            return changes.attributes[name];
                        }
                        return $this.attr(name);
                    }
                },
                style: {
                    get: function(name) {
                        if (changes.style[name]) {
                            return changes.style[name];
                        }
                        return $this.css(name);
                    }
                }
            };
            for (var index = 0; index < changing.length; index++) {
                var f = changing[index];
                f(changes);
            }
            var changed = {
                attributes: {},
                style: {}
            };
            var hasChanges = false;
            $.each(changes.attributes, function(key, value) {
                if (key != 'get' && $this.attr(key) != value) {
                    changes.attributes[key] = {
                        oldValue: $this.attr(key),
                        newValue: value
                    };
                    $this.attr(key, value);
                    hasChanges = true;
                }
            });
            $.each(changes.style, function(key, value) {
                if (key != 'get' && $this.css(key) != value) {
                    changed.style[key] = {
                        oldValue: $this.css(key),
                        newValue: value
                    };
                    $this.css(key, value);
                    hasChanges = true;
                }
            });
            if (hasChanges && data.sendChanges) {
                realtimeSend($this.attr('id'), 'changeableChanges', changed)
            }
        }
    };

    $.fn.changeable = function(method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.changeable');
            return this;
        }
    };
})(jQuery);