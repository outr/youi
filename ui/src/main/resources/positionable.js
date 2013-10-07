(function($) {
    var dataKey = 'positionable_settings';
    var methods = {
        init: function(options) {
            var settings = $.extend({
                positioning: [],
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
            if (key == 'positioning') {
                if (data['timerId'] == null && value.length > 0) {
                    methods.refreshInterval($this);     // Initialize the updates if it hasn't already been done
                } else if (data['timerId'] != null && value.length == 0) {
                    methods.cancelInterval($this);      // Stop updates if it is updating
                }
            } else if (key == 'frequency') {
                if (data['positioning'].length == 0) {
                    methods.cancelInterval($this);          // Stop updates if nothing to position
                } else {
                    methods.refreshInterval($this);         // Refresh the interval if there are things to position
                }
            } else if (key == 'timerId') {
                // Ignore
            } else if (key == 'sendChanges') {
                // Ignore
            } else {
                console.log('Unknown option for positionable: ' + key);
            }
        },
        refreshInterval: function($this) {
            methods.cancelInterval($this);
            var data = $this.data(dataKey);
            data['timerId'] = setInterval(function() {
                methods.updatePositionable($this);
            }, data['frequency']);
        },
        cancelInterval: function($this) {
            var data = $this.data(dataKey);
            var previousId = data['timerId'];
            if (previousId != null) {
                data['timerId'] = clearInterval(previousId);
            }
        },
        updatePositionable: function($this) {
            var data = $this.data(dataKey);
            var positioning = data['positioning'];
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
            for (var index = 0; index < positioning.length; index++) {
                var f = positioning[index];
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
                communicator.send('positioningChanges', $this.attr('id'), changed);
            }
        }
    };

    $.fn.positionable = function(method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.positionable');
            return this;
        }
    };
})(jQuery);