// Support serializing a form
jQuery.fn.serializeForm = function() {
    // Create an object to hold the data
    var formParams = {};

    // Iterate over the selector results
    this.each(function() {
        // Iterate over each child form element of this
        jQuery('input, select, textarea', this).each(function() {
            var jObject = jQuery(this);
            var id = jObject.attr('id');
            if (id != null && id != '') {       // Only add elements with an id
                var data = jObject.val();
                var type = jObject.attr('type');
                if ('checkbox' == type) {
                    formParams[id] = this.checked ? 'true' : 'false';
                } else if ('radio' == 'type') {
                    formParams[id] = data;
                } else if (formParams[id] === undefined) {
                    formParams[id] = encodeURIComponent(data);
                } else {        // Append
                    formParams[id] += ',' + encodeURIComponent(data);
                }
            }
        });
    });
    return formParams;
};