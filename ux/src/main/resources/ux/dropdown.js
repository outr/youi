(function ($) {
    $.fn.dropdown = function(args) {
        var functions = {
            init: function(options) {
                var defaults = $.extend({}, this.data('dropdownSettings'), $.fn.dropdown.defaults);
                var settings = $.extend({}, defaults, options);

                settings.mainSelector = this;
                this.data('dropdownSettings', settings);

                var ignoreEvent = false;

                // Listen for the show on event
                this.on(settings.showOn, function() {
                    toggleDropdown(settings);
                    ignoreEvent = true;                     // Make sure the hideOnClick ignores this event
                });

                // Configure hide on click
                $(document).click(function() {
                    if (ignoreEvent) {
                        ignoreEvent = false;                // The element was clicked on, so ignore this event
                    } else {
                        if (settings.hideOnClick) {
                            closeDropdown(settings);
                        }
                    }
                });

                if (settings.dropdownSelector == null) {
                    console.log('The option dropdownSelector must be set to a jQuery selector in order to properly show the drop-down.');
                } else if (settings.dropdownSelector.length == 0) {
                    console.log('The dropdownSelector is empty.');
                } else {
                    initializeDropdown(settings);
                }

                return this;
            },
            open: function() {
                var settings = this.data('dropdownSettings');
                openDropdown(settings);
            },
            close: function() {
                var settings = this.data('dropdownSettings');
                closeDropdown(settings);
            },
            isOpen: function() {
                var settings = this.data('dropdownSettings');
                return isOpenDropdown(settings);
            },
            toggle: function() {
                var settings = this.data('dropdownSettings');
                return toggleDropdown(settings);
            },
            option: function(key, value) {
                var settings = this.data('dropdownSettings');
                settings[key] = value;
                this.data('dropdownSettings', settings);
            }
        };

        if (functions[args]) {
            return functions[args].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof args === 'object' || !args) {
            return functions.init.apply(this, arguments);
        } else {
            $.error('Function ' + args + ' does not exist on jQuery.dropdown.');
        }

        function openDropdown(settings) {
            var instance = settings.mainSelector;
            var top = instance.offset().top + instance.outerHeight() + settings.offsetY;
            var left = instance.offset().left + settings.offsetX;
            settings.dropdownSelector.css('top', top);
            settings.dropdownSelector.css('left', left);
            settings.dropdownSelector.css('display', 'block');
        }

        function closeDropdown(settings) {
            settings.dropdownSelector.css('display', 'none');
        }

        function toggleDropdown(settings) {
            var open = isOpenDropdown(settings);
            if (open) {
                closeDropdown(settings);
            } else {
                openDropdown(settings);
            }
            return !open;
        }

        function isOpenDropdown(settings) {
            return settings.dropdownSelector.css('display') != 'none';
        }

        function initializeDropdown(settings) {
            if (settings.modifyDropdownCSS) {
                settings.dropdownSelector.css('display', 'none');
                settings.dropdownSelector.css('position', 'absolute');
            }
        }
    };

    $.fn.dropdown.defaults = {
        dropdownSelector: null,
        // Modifies the CSS to set position to absolute and display to none on initialization if set true.
        modifyDropdownCSS: true,
        // Defines what event should cause the dropdown to appear.
        showOn: 'click',
        // Hides the dropdown when mouse is clicked anywhere else on the screen.
        hideOnClick: true,
        offsetX: 0,
        offsetY: 0
    };
}(jQuery));

//$(document).ready(function() {
//    var button = $('#button');
//    var dropdown = $('#dropdown');
//    button.dropdown({
//        dropdownSelector: dropdown
//    });
//});