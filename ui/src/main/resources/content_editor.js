function getContainerElement() {
    var nodes = selectionNodes();
//    dumpNodes(nodes);
    if (nodes.firstNode.nodeType != 3) {
        return nodes.firstNode;
    } else {
        return nodes.commonContainer;
    }
}

function dumpNodes(nodes) {
    console.log('--- Dumping Nodes ---');
    for (var i = 0; i < nodes.nodes.length; i++) {
        var n = nodes.nodes[i];
        if (n.type == 'text') {
            console.log(n.type + ' - ' + n.text + ' Complete: ' + n.complete + ', Start: ' + n.start + ', End: ' + n.end);
        } else {
            console.log(n.type + ', Children: ' + n.children);
        }
    }
    console.log('----------------------');
}

function getSelectionStyle(key) {
    var containerEl = getContainerElement();

    if (containerEl) {
        if (window.getComputedStyle) {
            return window.getComputedStyle(containerEl, null)[key];
        } else if (containerEl.currentStyle) {
            return containerEl.currentStyle[key];
        }
    }
    return null;
}

function selectionNodes() {
    var s = rangy.getSelection();
    if (s.isCollapsed) {            // Special handling for collapsed
        var node = s.anchorNode;
        if (node.nodeType == Node.TEXT_NODE) {
            node = node.parentNode;         // Make sure it's an element and
        }
        return {
            nodes: [s.anchorNode],
            textComplete: false,
            firstNode: s.anchorNode,
            lastNode: s.anchorNode,
            commonContainer: node,
            properWrapper: false
        };
    }
    var foundEnd = false;
    var nodes = [];

    // Grab the parent if the selection represents the entire text block
    if (s.focusNode.nodeType == Node.TEXT_NODE && s.anchorNode == s.focusNode && s.anchorOffset == 0 && s.anchorNode.nodeValue.length == s.focusOffset && s.focusNode.parentNode.childNodes.length == 1) {
        console.log('grabbing parent...');
        s.focusNode = s.focusNode.parentNode;
        s.anchorNode = s.focusNode;
    }

    function verifyAndAdd(node) {
        if (node == s.focusNode) {
            foundEnd = true;
        }
        if (node.nodeType == Node.TEXT_NODE) {
            var start = 0;
            var textLength = node.nodeValue.length;
            var end = textLength;
            if (node == s.anchorNode) {
                start = s.anchorOffset;
            }
            if (node == s.focusNode) {
                end = s.focusOffset;
            }
            var length = end - start;
            if (length > 0) {
//                console.log('text: ' + node + ', start: ' + start + ', end: ' + end + ', Anchor? ' + (node == s.anchorNode) + ', Focus? ' + (node == s.focusNode) + ', Offsets: ' + s.anchorOffset + 'x' + s.focusOffset + ', Nodes: ' + s.anchorNode + 'x' + s.focusNode);
                nodes.push({
                    type: 'text',
                    node: node,
                    start: start,
                    end: end,
                    text: node.nodeValue.substring(start, end),
                    complete: length == textLength
                });
            }
        } else {
            nodes.push({
                type: 'containerStart',
                node: node,
                children: node.childNodes.length
            });
            var before = nodes.length;
            if (node.hasChildNodes()) {
                verifyAndAdd(node.firstChild);
            }
            nodes.push({
                type: 'containerEnd',
                node: node,
                children: node.childNodes.length,
                complete: nodes.length - before == node.childNodes.length
            });
        }
        if (!foundEnd) {
            var nextSibling = node.nextSibling;
            if (nextSibling != null) {
                verifyAndAdd(nextSibling);
            }
        }
    }

    // Build nodes array
    verifyAndAdd(s.anchorNode);

    var allComplete = true;
    for (var i = 0; i < nodes.length; i++) {
        var n = nodes[i];
        if (n.type == 'text') {
            if (!n.complete) {
                allComplete = false;
            }
        }
    }

    var firstNode = nodes[0];
    var lastNode = nodes[nodes.length - 1];
    var commonContainer = null;
    var properWrapper = false;
    if (allComplete && firstNode.type == 'containerStart' && lastNode.type == 'containerEnd' && firstNode.node == lastNode.node) {
        commonContainer = firstNode.node;
        properWrapper = true;
    } else {
        commonContainer = s.getRangeAt(0).commonAncestorContainer;
        // Make sure we have an element rather than a text node
        if (commonContainer.nodeType == 3) {
            commonContainer = commonContainer.parentNode;
        }
    }

    return {
        nodes: nodes,
        textComplete: allComplete,
        firstNode: firstNode.node,
        lastNode: lastNode.node,
        commonContainer: commonContainer,
        properWrapper: properWrapper
    };
}
function toggleStyle(key, value, aliases) {
    var nodes = selectionNodes();
    if (aliases == null) aliases = [];
    var styleValues = (value + ', ' + aliases.join(', ')).toLowerCase();

    var addStyle = true;
    var currentStyle = getSelectionStyle(key);
    if (currentStyle == null) currentStyle = 'null';
    currentStyle = currentStyle.toLowerCase();
    if (styleValues.indexOf(currentStyle) != -1) {
        addStyle = false;
    }
//    console.log('addStyle? ' + addStyle + ', Current: ' + currentStyle + ', Value: ' + value + ', Equal: ' + (currentStyle == value) + ', Style Values: ' + styleValues);
//    if (!nodes.properWrapper && currentStyle == value) {
        dumpNodes(nodes);
//    }

    // Remove all style entries for children
    clearStyles(nodes.nodes, key);

    if (addStyle) {
        if (nodes.properWrapper) {
            nodes.commonContainer.style[key] = value;      // We have a proper wrapper node
        } else {
            createStylized(key, value);                    // We have to create a new node wrapper for the selection
        }
    }
}

function clearStyles(nodes, key) {
    for (var i = 0; i < nodes.length; i++) {
        var n = nodes[i];
        if (n.type == 'containerEnd' && n.complete) {
            console.log('Clearing: ' + n.node.style[key] + ' (' + key + ') ' + n.node);
            n.node.style[key] = null;
        }
    }
}

function createStylized(key, value) {
//    console.log('createStylized! ' + key + ' = ' + value);
    var span = document.createElement('span');
    span.style[key] = value;
    var range = rangy.getSelection().getRangeAt(0);
    span.appendChild(range.extractContents());
    range.insertNode(span);
    range = rangy.createRange();
    range.selectNode(span);
    var s = rangy.getSelection();
    s.removeAllRanges();
    s.addRange(range);
}