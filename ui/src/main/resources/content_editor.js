/**
 * Toggles the style value state on the selection.
 *
 * @param styleName the css key camel-case.
 * @param styleValues the list of aliases for the value to match on. The first is what is set.
 * @param disabled the value to set if the value is already set on the entire selection.
 * @param nodes the list of nodes (will query if not provided).
 */
function toggleStyle(styleName, styleValues, disabled, nodes) {
    if (nodes == null) {
        nodes = selectedNodes();
    }
    var hasStyle = selectionHasStyle(styleName, styleValues, nodes);
//    console.log('Already has the style? ' + hasStyle);
    var spans = spansForSelected(nodes);
    for (var i = 0; i < spans.length; i++) {
        if (hasStyle) {
            spans[i].style[styleName] = null;       // Has the style, remove it.
        } else {
            spans[i].style[styleName] = styleValues[0]; // Apply the style
        }
    }

    // Re-select
    var range = rangy.createRange();
    range.setStartBefore(spans[0]);
    range.setEndAfter(spans[spans.length - 1]);
    var s = rangy.getSelection();
    s.removeAllRanges();
    s.addRange(range);
}

/**
 * Checks to see if the entire selection has the style value applied.
 *
 * @param styleName the style name in camel-case
 * @param styleValues an array of all the style values that are considered a match (ex. ['bold', '700'])
 * @param nodes optional array of selectedNodes. If this is null it will get the selectedNodes.
 * @returns {boolean} true only if the entire selection has the style value applied.
 */
function selectionHasStyle(styleName, styleValues, nodes) {
    if (nodes == null) {
        nodes = selectedNodes();
    }

    var valuesString = styleValues.join(', ').toLowerCase();
    var value = selectionStyle(styleName, nodes);
    return valuesString.indexOf(value) != -1;
}

/**
 * Retrieves the styleValue for the entire selection. Will return null if the style is not consistent across the entire
 * selection.
 *
 * @param styleName the style name to look up.
 * @param nodes optionally specify the nodes array (will be selected if not provided).
 * @returns {string} the current style value or null
 */
function selectionStyle(styleName, nodes) {
    if (nodes == null) {
        nodes = selectedNodes();
    }
    var value = '----';
    for (var i = 0; i < nodes.length; i++) {
        var computed = window.getComputedStyle(nodes[i].node.parentNode, null)[styleName];
        computed = computed.toLowerCase();
        if (value == '----') {
            value = computed;
        } else if (computed != value) {
            value = null;
            break;
        }
    }
    return value;
}

/**
 * Iterates over the supplied nodes (or gets them from selectedNodes if null) and makes sure every text node is within a
 * stylized span and properly breaks partial text blocks.
 *
 * @param nodes the nodes array from selectedNodes (will query itself if it is null).
 * @returns {Array} array of spans representing the current selection
 */
function spansForSelected(nodes) {
    if (nodes == null) {
        nodes = selectedNodes();
    }

    function createNewSpan(text, currentParent) {
        var span = document.createElement('span');
        span.classList.add('stylized');
        if (text != null) {
            span.appendChild(document.createTextNode(text));
        }
        if (currentParent != null) {
            span.style.cssText = currentParent.style.cssText;
        }
        return span;
    }

    function appendAfter(before, after) {
        if (before.nextSibling != null) {
            before.parentNode.insertBefore(after, before.nextSibling);
        } else {
            before.parentNode.appendChild(after);
        }
    }

    // Break out spans
    var spans = [];
    for (var i = 0; i < nodes.length; i++) {
        var nodeEntry = nodes[i];
        var node = nodeEntry.node;

        var parentStylized = null;
        if (node.parentNode.classList.contains('stylized')) {
            parentStylized = node.parentNode;
        }
        if (parentStylized != null && nodeEntry.start == 0 && nodeEntry.end == nodeEntry.length) {
            spans.push(parentStylized);         // Already fully encapsulated
        } else {
            var preSpan = null;
            var postSpan = null;
            var emptyBlock = document.createTextNode('');
            var currentNode = node;
            if (parentStylized != null) {
                currentNode = parentStylized;
            }
            currentNode.parentNode.replaceChild(emptyBlock, currentNode);
            var previous = emptyBlock;
            if (nodeEntry.start > 0) {
                var preText = node.nodeValue.substring(0, nodeEntry.start);
                preSpan = createNewSpan(preText, parentStylized);
            }
            var newNode = createNewSpan(nodeEntry.text, parentStylized);
            spans.push(newNode);
            if (nodeEntry.end != nodeEntry.length) {
                var postText = node.nodeValue.substring(nodeEntry.end);
                postSpan = createNewSpan(postText, parentStylized);
            }
            if (preSpan != null) {
                appendAfter(previous, preSpan);
                previous = preSpan;
            }
            appendAfter(previous, newNode);
            previous = newNode;
            if (postSpan != null) {
                appendAfter(previous, postSpan);
                previous = postSpan;
            }
        }
    }
    return spans;
}

/**
 * Gathers all the text nodes for the current selection range and returns them in a JSON format:
 * { node, start, end, length, text }
 *
 * @returns {Array}
 */
function selectedNodes() {
    var range = rangy.getSelection().getRangeAt(0);
    var nodes = [];
    if (range.collapsed) {
        nodes.push({
            node: range.startContainer,
            start: range.startOffset,
            end: range.endOffset,
            length: range.startContainer.nodeValue.length
        });
    } else {
//        console.log('selectedNodes... ' + range.startContainer + ', ' + range.endContainer);
        if (range.startContainer == range.endContainer && range.startContainer.nodeType != Node.TEXT_NODE) {
            var startContainer = range.startContainer.childNodes.item(range.startOffset);
            var endContainer = range.startContainer.childNodes.item(range.endOffset);
            range.startContainer = startContainer;
            range.startOffset = 0;
            range.endContainer = endContainer;
            range.endOffset = 0;
//            console.log('modified... ');
        }
        var node = range.startContainer;
        while(true) {
            var start = 0;
            var length = 0;
            if (node.nodeType == Node.TEXT_NODE) {
                length = node.nodeValue.length;
            } else {
                length = node.childNodes.length;
            }
            var end = length;
            if (node == range.startContainer) {
                start = range.startOffset;
            }
            if (node == range.endContainer) {
                end = range.endOffset;
            }
            if (node.nodeType == Node.TEXT_NODE) {
                if (end - start > 0) {
                    var t = {
                        node: node,
                        start: start,
                        end: end,
                        length: length,
                        text: node.nodeValue.substring(start, end)
                    };
                    nodes.push(t);
//                    console.log('text: ' + t.text + ' (' + t.start + ' - ' + t.end + ')');
                }
            } else {
//                console.log('skipping container: ' + node + ' ' + start + ' - ' + end + ' (' + length + ') ' + node.childNodes.item(start));
            }


            if (node != range.endContainer) {
                if (node.hasChildNodes()) {
                    node = node.childNodes.item(start);
                } else if (node.nextSibling != null) {
                    node = node.nextSibling;
                } else {
                    node = node.parentNode.nextSibling;
                }
            } else {
                break;
            }
        }
    }
    return nodes;
}