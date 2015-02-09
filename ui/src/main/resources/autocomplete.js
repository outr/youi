function split(v) {
    return v.split(/,\s*/);
}

function extractLast(term) {
    return split(term).pop();
}

function autocompletify(pageId, id, multiple, autoFocus, delay, appendId, disabled, minLength) {
    $('#' + id).autocomplete({
        source: function(request, response) {
            var searchTerm = request.term;
            if (multiple) {
                searchTerm = extractLast(searchTerm);
            }
            $.getJSON('/autocomplete/request', {
                pageId: pageId,
                fieldId: id,
                term: searchTerm
            }, response);
        },
        search: function() {
            if (multiple) {
                var term = extractLast(this.value);
                if (term.length < $(this).autocomplete('option', 'minLength')) {
                    return false;
                }
            }
        },
        focus: function() {
            if (multiple) {
                return false;
            }
        },
        select: function(event, ui) {
            var id = $(this).attr('id');
            if (multiple) {
                var terms = split(this.value);
                terms.pop();
                terms.push(ui.item.value);
                realtime.send({
                    type: 'autocompleteMultiSelect',
                    id: id,
                    values: terms
                });
                terms.push('');
                this.value = terms.join(', ');
                return false;
            }
            realtime.send({
                type: 'autocompleteSelect',
                id: id,
                value: ui.item.value
            });
        },
        autoFocus: autoFocus,
        delay: delay,
        appendTo: appendId,
        disabled: disabled,
        minLength: minLength,
        change: function() {
            realtime.fireChange($(this).get(0));
        }
    }).data('ui-autocomplete')._renderItem = function(ul, item) {
        return $('<li></li>').data('item.autocomplete', item).append($('<a></a>').html(item.label)).appendTo(ul);
    };
}