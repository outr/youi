// Support finding the index of a Node in the parent
Node.prototype.getParentIndex = function () {
    return Array.prototype.indexOf.call(this.parentNode.childNodes, this);
};

// Support capitalizing text
String.prototype.capitalize = function () {
    return this.replace(/[a-zA-Z0-9]+/g, function (txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
};

function initContentEditor(containerId) {
    var container = document.getElementById(containerId);

    var previousValue = container.innerHTML;
    var threshold = 500;
    // TODO: allow setting the threshold
    var checkContentEditorChange = function () {
        var currentValue = container.innerHTML;
        if (currentValue != previousValue) {
            groupedSend(containerId, threshold, 'contentChanged', containerId, {
                html: currentValue
            });
            previousValue = currentValue;
        }
    };
    container.addEventListener('input', checkContentEditorChange);
    container.addEventListener('styleChanged', checkContentEditorChange);
    container.addEventListener('manipulatedSelection', checkContentEditorChange);
}

/**
 * Toggles the style value state on the selection.
 *
 * @param containerId defines an optional container that the selection must be within to apply the style change.
 * @param styleName the css key camel-case.
 * @param styleValues the list of aliases for the value to match on. The first is what is set.
 * @param disabled the value to set if the value is already set on the entire selection.
 * @param nodes the list of nodes (will query if not provided).
 */
function toggleStyle(containerId, styleName, styleValues, disabled, nodes) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow style toggling if outside the container
    }
    if (nodes == null) {
        nodes = selectedTextNodes();
    }
    var hasStyle = selectionHasStyle(styleName, styleValues, nodes);
    var styleValue = styleValues[0];
    if (hasStyle) {
        styleValue = disabled;
    }
    setStyle(null, styleName, styleValue);
}

var blockRegex = /^(address|blockquote|body|center|dir|div|dl|fieldset|form|h[1-6]|hr|isindex|menu|noframes|noscript|ol|p|pre|table|ul|dd|dt|frameset|li|tbody|td|tfoot|th|thead|tr|html)$/i;

function toggleBlockStyle(containerId, styleName, styleValues, disabled) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow style toggling if outside the container
    }
    var blockContainer = findAncestor(rangy.getSelection().getRangeAt(0).commonAncestorContainer, function (n) {
        var displayValue = n.style['display'];
        return displayValue == 'block' || (displayValue == '' && blockRegex.test(n.nodeName));
    });
    if (blockContainer != null) {
        var valuesString = styleValues.join(', ').toLowerCase();
        var value = styleForNode(blockContainer, styleName);
        clearStylePropertyOnNode(blockContainer, styleName);        // Clear the style on this and all children before applying
        if (valuesString.indexOf(value) != -1) {
            blockContainer.style[styleName] = disabled;             // Toggle it off
        } else {
            blockContainer.style[styleName] = styleValues[0];       // Toggle it on
        }
        editorFor(blockContainer).dispatchEvent(new CustomEvent('styleChanged'));
    }
}

function clearStylePropertyOnNode(node, styleName) {
    node.style[styleName] = null;
    if (node.hasChildNodes()) {
        for (var i = 0; i < node.childNodes.length; i++) {
            var n = node.childNodes.item(i);
            if (n.nodeType == Node.ELEMENT_NODE) {
                clearStylePropertyOnNode(n, styleName);
            }
        }
    }
}

function setStyle(containerId, styleName, styleValue) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow styling if outside the container
    }
    if (rangy.getSelection().getRangeAt(0).collapsed) {
        var container = rangy.getSelection().getRangeAt(0).commonAncestorContainer;
        if (container.nodeType == Node.TEXT_NODE) {
            container = container.parentNode;
        }
        setBlockStyle(null, styleName, styleValue, container, false);
    } else {
        var spans = selectedSpans();
        for (var i = 0; i < spans.length; i++) {
            spans[i].style[styleName] = styleValue;
        }

        if (spans != null && spans.length > 0) {
            // Fire event
            var range = rangy.getSelection().getRangeAt(0);
            editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('styleChanged'));
        }
    }
}

function setBlockStyle(containerId, styleName, styleValue, blockContainer, overrideChildren) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow style toggling if outside the container
    }
    if (overrideChildren == null) {
        overrideChildren = true;
    }
    if (blockContainer == null) {
        blockContainer = findAncestor(rangy.getSelection().getRangeAt(0).commonAncestorContainer, function (n) {
            var displayValue = n.style['display'];
            return displayValue == 'block' || (displayValue == '' && blockRegex.test(n.nodeName));
        });
    }
    if (blockContainer != null) {
        if (overrideChildren) {
            clearStylePropertyOnNode(blockContainer, styleName);        // Clear the style on this and all children before applying
        }
        blockContainer.style[styleName] = styleValue;
        editorFor(blockContainer).dispatchEvent(new CustomEvent('styleChanged'));
    }
}

function adjustStyle(containerId, styleName, adjuster) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow styling if outside the container
    }
    var spans = selectedSpans();
    for (var i = 0; i < spans.length; i++) {
        spans[i].style[styleName] = adjuster(styleForNode(spans[i], styleName));
    }

    if (spans != null && spans.length > 0) {
        // Fire event
        var range = rangy.getSelection().getRangeAt(0);
        editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('styleChanged'));
    }
}

function manipulateSelection(containerId, manipulator) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow styling if outside the container
    }
    var spans = selectedSpans();
    manipulator(spans);

    // Fire event
    var range = rangy.getSelection().getRangeAt(0);
    editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('manipulatedSelection'));
}

function removeFormattingFromSelection(containerId) {
    manipulateSelection(containerId, function (spans) {
        for (var i = 0; i < spans.length; i++) {
            spans[i].style.cssText = '';            // Clear the style
        }
    });
}

function selectionInContainer(containerId) {
    var range = rangy.getSelection().getRangeAt(0);
    var container = document.getElementById(containerId);
    var element = range.commonAncestorContainer;
    while (element != null) {
        if (element == container) return true;
        element = element.parentNode;
    }
    return false;
}

function editorFor(container) {
    if (container.contentEditable == 'true') {
        return container;
    }
    return editorFor(container.parentNode);
}

/**
 * Checks to see if the entire selection has the style value applied.
 *
 * @param styleName the style name in camel-case
 * @param styleValues an array of all the style values that are considered a match (ex. ['bold', '700'])
 * @param nodes optional array of selectedTextNodes. If this is null it will get the selectedTextNodes.
 * @returns {boolean} true only if the entire selection has the style value applied.
 */
function selectionHasStyle(styleName, styleValues, nodes) {
    if (nodes == null) {
        nodes = selectedTextNodes();
    }

    var valuesString = styleValues.join(', ').toLowerCase();
    var value = selectionStyle(styleName, nodes);
    return valuesString.indexOf(value) != -1;
}

function selectionBlockHasStyle(styleName, styleValues, blockContainer) {
    if (blockContainer == null) {
        blockContainer = findAncestor(rangy.getSelection().getRangeAt(0).commonAncestorContainer, function (n) {
            var displayValue = n.style['display'];
            return displayValue == 'block' || (displayValue == '' && blockRegex.test(n.nodeName));
        });
    }
    var valuesString = styleValues.join(', ').toLowerCase();
    var value = styleForSelectedBlock(styleName, blockContainer);
    return valuesString.indexOf(value) != -1;
}

function styleForNode(node, styleName) {
    if (node.nodeType == Node.TEXT_NODE) {
        return window.getComputedStyle(node.parentNode, null)[styleName];
    } else {
        return window.getComputedStyle(node, null)[styleName];
    }
}

function styleForSelectedBlock(styleName, blockContainer) {
    if (blockContainer == null) {
        blockContainer = findAncestor(rangy.getSelection().getRangeAt(0).commonAncestorContainer, function (n) {
            var displayValue = n.style['display'];
            return displayValue == 'block' || (displayValue == '' && blockRegex.test(n.nodeName));
        });
    }
    if (blockContainer != null) {
        return styleForNode(blockContainer, styleName);
    }
    return null;
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
        nodes = selectedTextNodes();
    }
    var value = '----';
    for (var i = 0; i < nodes.length; i++) {
        var computed = window.getComputedStyle(nodes[i].node.parentNode, null)[styleName];
        if (computed != null) {
            computed = computed.toLowerCase();
        }
        if (value == '----') {
            value = computed;
        } else if (computed != value) {
            value = null;
            break;
        }
    }
    if (value == '----') {
        value = null;
    }
    return value;
}

function selectedSpans() {
    function createNewSpan(text, styleParent) {
        var span = document.createElement('span');
        var $span = $(span);
        $span.addClass('stylized');
        if (text != null) {
            span.appendChild(document.createTextNode(text));
        }
        if (styleParent != null) {
            span.style.cssText = styleParent.style.cssText;
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

    // Create spans array
    var range = rangy.getSelection().getRangeAt(0);
    range.splitBoundaries();
    var nodes = range.getNodes([Node.TEXT_NODE]);
    var spans = [];
    for (var i = 0; i < nodes.length; i++) {
        var node = nodes[i];
        if ($(node.parentNode).hasClass('stylized')) {
            if (node.parentNode.childNodes.length == 1) {
                spans.push(node.parentNode);
            } else if (node.parentNode.parentNode != null) {
                var emptyBlock = document.createTextNode('');
                var parentNode = node.parentNode;
                var currentNode = emptyBlock;
                parentNode.parentNode.replaceChild(emptyBlock, parentNode);
                for (var j = 0; j < parentNode.childNodes.length; j++) {
                    var childNode = parentNode.childNodes.item(j);
                    if (childNode.nodeType != Node.TEXT_NODE) {
                        childNode.cssText += ';' + parentNode.style.cssText;
                        appendAfter(currentNode, childNode);
                        currentNode = childNode;
                    } else {
                        var span = createNewSpan(childNode.nodeValue, parentNode);
                        appendAfter(currentNode, span);
                        currentNode = span;
                        if (childNode == node) {
                            spans.push(span);
                        }
                    }
                }
            }
        } else {
            var span = createNewSpan(node.nodeValue);
            node.parentNode.replaceChild(span, node);
            spans.push(span);
        }
    }

    // Select the spans
    if (spans.length > 0) {
        var newRange = rangy.createRange();
        newRange.setStartBefore(spans[0]);
        newRange.setEndAfter(spans[spans.length - 1]);
        var s = rangy.getSelection();
        s.removeAllRanges();
        s.addRange(newRange);
    }

    return spans;
}

/**
 * Returns an array of text nodes for the current selection. Splits boundaries so no partial nodes are represented.
 *
 * @returns Array[Text]
 */
function selectedTextNodes() {
    var range = rangy.getSelection().getRangeAt(0);
    if (range.collapsed && range.startContainer.nodeType == Node.TEXT_NODE) {
        return [{
            node: range.startContainer,
            start: range.startOffset,
            end: range.endOffset,
            length: range.startContainer.nodeValue.length,
            text: ''
        }]
    } else {
        var nodes = range.getNodes([Node.TEXT_NODE]);
        var textNodes = [];
        for (var i = 0; i < nodes.length; i++) {
            var n = {
                node: nodes[i],
                start: 0,
                length: nodes[i].nodeValue.length
            };
            if (nodes[i] == range.startContainer) {
                n.start = range.startOffset;
            }
            if (nodes[i] == range.endContainer) {
                n.end = range.endOffset;
            }
            n.text = n.node.nodeValue.substring(n.start, n.end);
            if (n.text != '') {
                textNodes.push(n);
            }
        }
        return textNodes;
    }
}

/**
 * Insert an image into the range.
 *
 * @param containerId defines an optional container that the selection must be within to insert the image.
 * @param source the image source
 */
function insertImage(containerId, source) {
    var img = document.createElement('img');
    img.src = source;
    insertNode(containerId, img);
}

function insertNode(containerId, node) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow if outside the container
    }
    var range = rangy.getSelection().getRangeAt(0);
    range.deleteContents();
    range.insertNode(node);

    editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('input'));
}

function insertIntoSelection(containerId, html) {
    var node = $.parseHTML(html)[0];
    insertNode(containerId, node);
}

function insertAroundSelection(containerId, html) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow if outside the container
    }
    var range = rangy.getSelection().getRangeAt(0);
    if (range.canSurroundContents()) {
        var node = $.parseHTML(html)[0];
        range.surroundContents(node);

        editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('input'));
    }
}

function insertAroundBlock(containerId, html) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow if outside the container
    }
    var range = rangy.getSelection().getRangeAt(0);
    if (range.collapsed) {
        var node = range.startContainer;
        var startOffset = range.startOffset;
        var endOffset = range.endOffset;
        var parent = range.commonAncestorContainer;
        if (parent.nodeType == Node.TEXT_NODE) {
            parent = parent.parentNode;
        }
        if ($(parent).hasClass('stylized')) {
            parent = parent.parentNode;     // Don't include stylized as a common ancestor
        }
        $(parent).wrapInner(html);

        var newRange = rangy.createRange();
        newRange.setStart(node, startOffset);
        newRange.setEnd(node, endOffset);
        var s = rangy.getSelection();
        s.removeAllRanges();
        s.addRange(newRange);

        editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('input'));
    } else if (range.canSurroundContents()) {
        var node = $.parseHTML(html)[0];
        range.surroundContents(node);

        editorFor(range.commonAncestorContainer).dispatchEvent(new CustomEvent('input'));
    }
}

function removeFromBlock(containerId, tagName) {
    if (containerId != null) {
        if (!selectionInContainer(containerId)) return;       // Don't allow if outside the container
    }
    var range = rangy.getSelection().getRangeAt(0);
    var node = range.startContainer;
    var startOffset = range.startOffset;
    var endOffset = range.endOffset;
    var tag = findByTagName(range.startContainer, tagName);
    if (tag != null) {
        var parent = tag.parentNode;
        var $tag = $(tag);
        $tag.replaceWith($tag.html());

        // Re-select
        var newRange = rangy.createRange();
        newRange.setStart(node, startOffset);
        newRange.setEnd(node, endOffset);
        var s = rangy.getSelection();
        s.removeAllRanges();
        s.addRange(newRange);

        editorFor(parent).dispatchEvent(new CustomEvent('input'));
    }
}

function findByTagName(node, tagName) {
    return findAncestor(node, function (n) {
        return n.nodeName.toLowerCase() == tagName.toLowerCase();
    });
}

function findAncestor(node, validator) {
    if (node.nodeType == Node.ELEMENT_NODE && validator(node)) {
        return node;
    }
    var parent = node.parentNode;
    if (parent != null) {
        return findAncestor(parent, validator);
    }
    return null;
}

/**
 * Adds a listener to detect when the style name value changes for the selection on the supplied container.
 *
 * @param container the container to restrict change detection to
 * @param styleName the style name to check
 * @param listener the listener to invoke (function(oldValue, newValue))
 */
function addSelectionStyleChangeListener(container, styleName, listener) {
    var previousStyle = null;
    var checkStyle = function () {
        var currentStyle = selectionStyle(styleName);           // Get the current style on the selection
        if (currentStyle != previousStyle) {
            listener(previousStyle, currentStyle);
            previousStyle = currentStyle;
        }
    };
    container.addEventListener('mouseup', checkStyle);
    container.addEventListener('keyup', checkStyle);
    container.addEventListener('styleChanged', checkStyle);
}

function contentEditorBindInput(inputId, containerId, styleName, format, visual2Internal) {
    var container = document.getElementById(containerId);
    var input = window.parent.document.getElementById(inputId);
    var internal2Visual = {};
    for (var key in visual2Internal) {
        var value = visual2Internal[key];
        internal2Visual[value] = key;
    }

    addSelectionStyleChangeListener(container, styleName, function (oldValue, newValue) {
        var v = newValue;
        if (v == null) {
            v = '';
        }
        if (internal2Visual.hasOwnProperty(v)) {
            v = internal2Visual[v];
        }
        if (format) {
            v = v.capitalize().replace(/\s*,\s*/g, ', ');
        }
        input.value = v;
        input.dispatchEvent(new CustomEvent('valueChanged'));
    });
    $(input).change(function(event) {
        var v = input.value;
        if (visual2Internal.hasOwnProperty(v)) {
            v = visual2Internal[v];
        }
        setStyle(containerId, styleName, v);
        return realtimeEvent(event, null, null, true, false, 0);
    });
}

function contentEditorBindFontStyle(inputId, containerId) {
    var container = document.getElementById(containerId);
    var input = window.parent.document.getElementById(inputId);
    var weight2Internal = {'Thin': '100', 'Light': '300', 'Medium': '500', 'Extra-Bold': '800', 'Ultra-Bold': '900'};
    var weight2Visual = {
        '100': 'Thin',
        '300': 'Light',
        '400': 'Normal',
        '500': 'Medium',
        '700': 'Bold',
        '800': 'Extra-Bold',
        '900': 'Ultra-Bold'
    };
    var weight = null;
    var style = null;
    var updateInput = function () {
        var s = '';
        if (weight != null && (weight.toLowerCase() != 'normal' || style == null || style.toLowerCase() == 'normal')) {
            s = weight;
        }
        if (style != null && style.toLowerCase() != 'normal') {
            if (s != '') {
                s += ' ';
            }
            s += style;
        }
        input.value = s.capitalize();
    };
    addSelectionStyleChangeListener(container, 'fontWeight', function (oldValue, newValue) {
        var w = newValue;
        if (w in weight2Visual) {
            w = weight2Visual[w];
        }
        weight = w;
        updateInput();
    });
    addSelectionStyleChangeListener(container, 'fontStyle', function (oldValue, newValue) {
        var s = newValue;
        if (s == null) {
            s = 'normal';
        }
        style = s.capitalize();
        updateInput();
    });
    input.addEventListener('change', function () {
        var index = input.value.lastIndexOf(' ');
        var parser = [input.value];
        if (index > 0) {
            parser = [input.value.substring(0, index), input.value.substring(index + 1)];
        }
        for (var i = 0; i < parser.length; i++) {
            parser[i] = parser[i].capitalize();
        }
        var w = 'normal';
        var s = 'normal';
        if (parser.length == 1) {
            if (parser[0].toLowerCase() == 'italic' || parser[0].toLowerCase() == 'oblique') {
                s = parser[0];
            } else {
                w = parser[0];
                if (w in weight2Internal) {
                    w = weight2Internal[w];
                }
            }
        } else {
            w = parser[0];
            if (w in weight2Internal) {
                w = weight2Internal[w];
            }
            s = parser[1];
        }
        w = w.toLowerCase();
        s = s.toLowerCase();
        console.log('value: ' + input.value + ' - weight: ' + w + ', style: ' + s);
        setStyle(containerId, 'fontWeight', w);
        setStyle(containerId, 'fontStyle', s);
    });
}

function contentEditorBindToggle(tagId, containerId, styleName, styleValues, activeClass, inactiveClass, block) {
    var tag = $(window.parent.document.getElementById(tagId));
    addSelectionStyleChangeListener(document.getElementById(containerId), styleName, function () {
        var hasStyleFunction = selectionHasStyle;
        if (block) hasStyleFunction = selectionBlockHasStyle;
        if (hasStyleFunction(styleName, styleValues)) {
            if (inactiveClass != null) {
                tag.removeClass(inactiveClass);
            }
            if (activeClass != null) {
                tag.addClass(activeClass);
            }
        } else {
            if (activeClass != null) {
                tag.removeClass(activeClass);
            }
            if (inactiveClass != null) {
                tag.addClass(inactiveClass);
            }
        }
    });
}