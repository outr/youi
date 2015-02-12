(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
(function (global){
global.contentEditor = {
    createClassWrapper: require("./contenteditor-wrapper.js").createClassWrapper,
    createHtmlWrapper: require("./contenteditor-wrapper.js").createHtmlWrapper,

    /**
     * Find function for text search.
     * @param   {String/RegExp}              searchTerm  Search term as a string or RegEx object.
     * @param   {String/Array/NodeList/Node} searchScope Scope set in terms of CSS query string/Node/NodeList under which sarch will be taken.
     * @param   {Object}                     options     Options, which determine search behavior.
     * @returns {Boolean}                    Search results Array with range objects.
     */
    find: function(searchTerm, searchScope, caseSensitive) {
        var options = options || {},
            result = [];

        caseSensitive = caseSensitive === undefined ? true : caseSensitive;

        if (!searchTerm || searchTerm === "") {
            return false;
        } else if (options && options.regEx) {
            searchTerm = new RegExp(searchTerm, caseSensitive ? "g" : "gi");
        }

        if (searchScope) {
            if (searchScope.nodeType) {
                searchScope = [searchScope];
            } else {
                if (typeof searchScope === "string") {
                    searchScope = document.querySelectorAll(searchScope);
                }
                searchScope = Array.prototype.slice.call(searchScope);
            }
        } else {
            searchScope = [document.body];
        }

        for (var i = 0, searchScopeRange = rangy.createRange(), searchResultRange; i < searchScope.length; i++) {
            searchScopeRange.selectNodeContents(searchScope[i]);
            options.withinRange = searchScopeRange;
            searchResultRange = rangy.createRange();

            while (searchResultRange.findText(searchTerm, options)) {
                result.push(searchResultRange.cloneRange());
                searchResultRange.collapse(false);
            }
        }

        return result;
    },

    /**
     * Replace function
     * @param {String}   replaceString  String to replace range(s) with.
     * @param {[[Type]]} range Range object/Array of Range objects on which replace will happen.
     */
    replace: function(replaceString, range) {
        range = range || rangy.getSelection().getRangeAt(0);

        if (Array.isArray(range)) {
            range.forEach(function(rn) {
                rn.deleteContents();
                rn.insertNode(document.createTextNode(replaceString));
            });
        } else {
            range.deleteContents();
            range.insertNode(document.createTextNode(replaceString));
        }
    },

    /**
     * Creates rangy range object for node provided or entire document if node is not provided
     * @param   {Node}   node Node on for which to create a range.
     * @returns {Object} Range object representing provided node (or document.body if none is provided) contents.
     */
    getRange: function(node) {
        var range = rangy.createRange();

        range.selectNodeContents(node || document.body);
        return range;
    },

    /**
     * Returns rangy selection object
     * @returns {Object} Rangy selection object
     */
    getSelection: function() {
        return rangy.getSelection();
    },

    /**
     * Set selection from provided selection/range object or array of ranges
     * @param {Object/Array} range Selection/range object or array of ranges to set selection to
     */
    setSelection: function(range) {
        if (range.getRangeAt) range = range.getRangeAt(0);
        if (Array.isArray(range)) {
            rangy.getSelection().setRanges(range);
        } else {
            rangy.getSelection().setSingleRange(range);
        }
    },

    /**
     * Function defining Tab behavior for scope(s) provided
     * @param {String/Object}              tab   String or HtmlWrapper object representing tab (default is "&emsp;")
     * @param {Array/NodeList/Node/Strinf} scope Scope set in terms of CSS query string/Node/NodeList under which Tab behavior will be redefined.
     */
    defineTab: function(tab, scope) {
        var tabHandler = this.tabHandler;

        tab = tab || " ";

        if (scope) {
            if (typeof scope === "string") {
                scope = document.querySelectorAll(scope);
            } else if (scope.nodeType) {
                scope = [scope];
            }
        } else {
            scope = document.querySelectorAll("[contenteditable]");
        }
        scope = Array.prototype.slice.call(scope);

        if (tabHandler) {
            scope.forEach(function(node) {
                node.removeEventListener("keydown", tabHandler, false);
            });
        }

        tabHandler = this.tabHandler = function(event) {
            var selRange, tabRange, charTab;
            if (event.keyCode === 9) {
                event.preventDefault();
                tabRange = rangy.createRange();
                if (typeof tab === "string") {
                    charTab = document.createTextNode(tab);
                    selRange = rangy.getSelection().getRangeAt(0);
                    selRange.deleteContents();
                    selRange.insertNode(charTab);
                } else {
                    charTab = tab.insert();
                }

                tabRange.collapseAfter(charTab);
                rangy.getSelection().setSingleRange(tabRange);
            }
        }

        scope.forEach(function(node) {
            node.addEventListener("keydown", tabHandler, false);
        });
    },

    /**
     * Change block formatting for selection.
     * @param {String} blockTag String that specifies block tag name to apply.
     */
    formatBlock: function(blockTag) {
        document.execCommand("formatBlock", false, blockTag.toLowerCase());
    },

    /**
     * Remove all formatting from selection.
     */
    clearFormating: function() {
        document.execCommand("removeFormat");
    },

    /**
    * Returns 'selectionInfo' object.
    * @param {Array/NodeList/Node/String} scope Scope set in terms of CSS query string/Node/NodeList that determines highest ancestor Node in ancestor chain. All styles will be merged in scopedStyle property of results object (excluding before mentioned highest ancestor).
    * @param {Function} filter Filter function used to filter out nodes styles that will be returned by 'nodesStyles' property of 'selectionInfo' object.
    */
    selectionInfo: function(scope, filter) {
        var node = rangy.getSelection().getRangeAt(0).startContainer,
            selectionInfo = {},
            computedStyle,
            scopedStyle,
            className,
            tagName;

        node = node.nodeType === 1 ? node : node.parentElement;
        scope = scope || document.body;

        selectionInfo.className = node.className;
        selectionInfo.tagName = node.tagName;
        selectionInfo.computedStyle = window.getComputedStyle(node);
        scopedStyle = node.cloneNode().style;
        for (var i = 0, elementStyle; node.parentElement !== scope;) {
            node = node.parentElement;
            elementStyle = node.style;
            for (var style in elementStyle) {
                if (!!elementStyle[style] && (typeof elementStyle[style] === "string") && !scopedStyle[style]) {
                    scopedStyle[style] = elementStyle[style];
                }
            }
        }
        selectionInfo.scopedStyle = scopedStyle;

        Object.defineProperty(selectionInfo, 'nodesStyles', {
            get: function() {
                var styles = [],
                    nodes = rangy.getSelection().getRangeAt(0).getNodes([1], filter);

                nodes.forEach(function(node) {
                    styles.push(node.style);
                });

                return styles;
            }
        });

        return selectionInfo;
    }
}

if (!document.hasOwnProperty("onselectionchange")) {
    require("./contenteditor-util.js").selectionChangePolyfill.start();
}

}).call(this,typeof global !== "undefined" ? global : typeof self !== "undefined" ? self : typeof window !== "undefined" ? window : {})
},{"./contenteditor-util.js":2,"./contenteditor-wrapper.js":3}],2:[function(require,module,exports){
module.exports = {};

module.exports.selectionChangePolyfill = (function (undefined) {

  var SELECT_ALL_MODIFIER = /^Mac/.test(navigator.platform) ? 'metaKey' : 'ctrlKey';
  var RANGE_PROPS = ['startContainer', 'startOffset', 'endContainer', 'endOffset'];

  var ranges;

  return {
    start: function (doc) {
      var d = doc || document;
      if (ranges || !hasNativeSupport(d) && (ranges = newWeakMap())) {
        if (!ranges.has(d)) {
          ranges.set(d, getSelectionRange(d));
          on(d, 'keydown', onKeyDown);
          on(d, 'mousedown', onMouseDown);
          on(d, 'mousemove', onMouseMove);
          on(d, 'mouseup', onMouseUp);
          on(d.defaultView, 'focus', onFocus);
        }
      }
    },
    stop: function (doc) {
      var d = doc || document;
      if (ranges && ranges.has(d)) {
        ranges['delete'](d);
        off(d, 'keydown', onKeyDown);
        off(d, 'mousedown', onMouseDown);
        off(d, 'mousemove', onMouseMove);
        off(d, 'mouseup', onMouseUp);
        off(d.defaultView, 'focus', onFocus);
      }
    }
  };

  function hasNativeSupport(doc) {
    var osc = doc.onselectionchange;
    if (osc !== undefined) {
      try {
        doc.onselectionchange = 0;
        return doc.onselectionchange === null;
      } catch (e) {
      } finally {
        doc.onselectionchange = osc;
      }
    }
    return false;
  }

  function newWeakMap() {
    if (typeof WeakMap !== 'undefined') {
      return new WeakMap();
    } else {
      console.error('selectionchange: WeakMap not supported');
      return null;
    }
  }

  function getSelectionRange(doc) {
    var s = doc.getSelection();
    return s.rangeCount ? s.getRangeAt(0) : null;
  }

  function on(el, eventType, handler) {
    el.addEventListener(eventType, handler, true);
  }

  function off(el, eventType, handler) {
    el.removeEventListener(eventType, handler, true);
  }

  function onKeyDown(e) {
    var code = e.keyCode;
    if (code === 65 && e[SELECT_ALL_MODIFIER] && !e.shiftKey && !e.altKey || // Ctrl-A or Cmd-A
        (code <= 40 && code >= 37) && e.shiftKey) { // (Alt-)Shift-arrow
      setTimeout(dispatchIfChanged.bind(null, this), 0);
    }
  }

  function onMouseDown(e) {
    if (e.button === 0) {
      on(this, 'mousemove', onMouseMove);
      setTimeout(dispatchIfChanged.bind(null, this), 0);
    }
  }

  function onMouseMove(e) {  // only needed while primary button is down
    if (e.buttons & 1) {
      dispatchIfChanged(this);
    } else {
      off(this, 'mousemove', onMouseMove);
    }
  }

  function onMouseUp(e) {
    if (e.button === 0) {
      setTimeout(dispatchIfChanged.bind(null, this), 0);
    } else {
      off(this, 'mousemove', onMouseMove);
    }
  }

  function onFocus() {
    setTimeout(dispatchIfChanged.bind(null, this.document), 0);
  }

  function dispatchIfChanged(doc) {
    var rOld = ranges.get(doc);
    var rNew = getSelectionRange(doc);
    if (!sameRange(rNew, rOld)) {
      ranges.set(doc, rNew);
      setTimeout(doc.dispatchEvent.bind(doc, new Event('selectionchange')), 0);
    }
  }

  function sameRange(r1, r2) {
    return r1 === r2 || r1 && r2 && RANGE_PROPS.every(function (prop) {
      return r1[prop] === r2[prop];
    });
  }
})();

},{}],3:[function(require,module,exports){
module.exports = {
    createClassWrapper: createClassWrapper,
    createHtmlWrapper: createHtmlWrapper
}

/**
 * Creates ClassWrapper object with specified options
 * @param   {String}        className Class name for ClassWrapper.
 * @param   {Object/String} options   Either options object for ClassWrapper or CSS style string.
 * @returns {Boolean}       ClassWrapper object with adjustable properties, rangy ClassApplier reference and apply/undo/toggle methods.
 */
function createClassWrapper(className, options) {
    var rangyClassApplier = rangy.createClassApplier(className, {applyToEditableOnly: true}),
        classWrapper = {},
        _context = {},
        _scope;

    classWrapper.rangy = rangyClassApplier;
    rangyClassApplier.elementProperties.style = {};

    Object.defineProperties(classWrapper, {
        className: {
            get: function() {return rangyClassApplier.className},
            set: function(className) {
                rangyClassApplier.className = className;
                rangyClassApplier.cssClass = className;
                rangyClassApplier.elementSortedClassName = className;
            }
        },
        tagName: {
            get: function() {return rangyClassApplier.elementTagName;},
            set: function(tagName) {
                tagName = tagName.toLowerCase();
                rangyClassApplier.elementTagName = tagName;
                rangyClassApplier.tagNames = [tagName];
            }
        },
        style: {
            get: function() {return rangyClassApplier.elementProperties.style;},
            set: function(style) {rangyClassApplier.elementProperties.style = style;}
        },
        properties: {
            get: function() {return rangyClassApplier.elementProperties;},
            set: function(properties) {
                rangyClassApplier.attrExceptions = [];
                for (var prop in properties) {
                    rangyClassApplier.elementProperties[prop] = properties[prop];
                    rangyClassApplier.attrExceptions.push(prop);
                }
            }
        },
        normalize: {
            get: function() {return rangyClassApplier.normalize;},
            set: function(normalize) {rangyClassApplier.normalize = normalize;}
        },
        context: {
            get: function() {return _context;},
            set: function(context) {
                for (var prop in context) {
                    _context[prop] = context[prop];
                }
            }
        }
    });

    Object.defineProperties(_context, {
        editableOnly: {
            get: function() {return rangyClassApplier.applyToEditableOnly;},
            set: function (editableOnly) {
                rangyClassApplier.applyToEditableOnly = editableOnly;
            }
        },
        scope: {
            get: function () {return _scope;},
            set: function (scope) {
                if (typeof scope === "string") {
                    scope = document.querySelectorAll(scope);
                }
                _scope = Array.prototype.slice.call(scope);
            }
        },
        tagNames: {
            get: function () {return rangyClassApplier.tagNames},
            set: function (tagNames) {
                if (typeof tagNames === "string") {
                    if (tagNames === "*") {
                        rangyClassApplier.applyToAnyTagName = true;
                    } else {
                        tagNames = tagNames.replace(/\s+/g,"").split(",");
                    }
                } else if (!Array.isArray(tagNames)) {
                    tagNames = [tagNames]
                }
                rangyClassApplier.tagNames = tagNames;
            }
        }
    });

    function checkScope(nodeList, range) {
        for (var i = 0, nodeRange = rangy.createRange(); i < nodeList.length; i++) {
            nodeRange.selectNodeContents(nodeList[i]);
            if (nodeRange.containsRange(range)) return true;
        }
        return false;
    }

    function dispatchStyleChange(range, classWrapper) {
        range.commonAncestorContainer.dispatchEvent(new CustomEvent('stylechange', {
            bubbles : true,
            detail: {range: range, classWrapper: classWrapper}
        }));
    }


    classWrapper.apply = function(range, rangesToPreserve) {
        if (range) {
            if (Array.isArray(range)) {
                range.forEach(function(rn) {
                    rn.refresh();
                    if (!_scope || checkScope(_scope, rn)) {
                        classWrapper.rangy.applyToRange(rn, range);
                        dispatchStyleChange(rn, this);
                    }
                });
            } else {
                if (!_scope || checkScope(_scope, range)) {
                    classWrapper.rangy.applyToRange(range, rangesToPreserve);
                    dispatchStyleChange(range, this);
                }
            }
        } else {
            range = rangy.getSelection().getRangeAt(0);
            if (!_scope || checkScope(_scope, range)) {
                var className = this.className,
                    styles = this.style,
                    nodes, startNode, selection;

                if (this.tagName.toLowerCase() === "a") document.execCommand("unlink");
                classWrapper.rangy.applyToSelection();

                selection = rangy.getSelection();
                range = selection.getRangeAt(0);
                nodes = range.getNodes([1], function(node) {return node.className === className});

                if (selection.isBackwards()) {
                    startNode = nodes[nodes.length - 1];

                    for (var style in styles) { // check for style consistency
                        if (startNode.style[style] !== styles[style]) {
                            startNode = nodes[nodes.length - 2];
                            break;
                        }
                    }

                    range.setStart(startNode.childNodes[0]);
                } else {
                    startNode = nodes[0];

                    for (var style in styles) { // check for style consistency
                        if (startNode.style[style] !== styles[style]) {
                            startNode = nodes[1];
                            break;
                        }
                    }

                    range.setStart(startNode.childNodes[0]);
                }

                selection.setSingleRange(range);
                dispatchStyleChange(range, this);
            }
        }
    }
    classWrapper.undo = function(range, rangesToPreserve) {
        if (range) {
            if (Array.isArray(range)) {
                range.forEach(function(rn) {
                    rn.refresh();
                    if (!_scope || checkScope(_scope, rn)) {
                        classWrapper.rangy.undoToRange(rn, range);
                        dispatchStyleChange(rn, this);
                    }
                });
            } else {
                if (!_scope || checkScope(_scope, range)) {
                    classWrapper.rangy.undoToRange(range, rangesToPreserve);
                    dispatchStyleChange(range, this);
                }
            }
        } else {
            range = rangy.getSelection().getRangeAt(0);
            if (!_scope || checkScope(_scope, range)) {
                this.tagName.toLowerCase() === "a" ? document.execCommand("unlink") : classWrapper.rangy.undoToSelection();
                dispatchStyleChange(range, this);
            }
        }
    }
    classWrapper.toggle = function(range) {
        if (range) {
            if (Array.isArray(range)) {
                range.forEach(function(rn) {
                    rn.refresh();
                    if (!_scope || checkScope(_scope, rn)) {
                        classWrapper.rangy.toggleRange(rn, range);
                        rn.commonAncestorContainer.dispatchEvent(new CustomEvent('style', { 'detail': {range: rn, classWrapper: this} }));
                    }
                });
            } else {
                if (!_scope || checkScope(_scope, range)) {
                    classWrapper.rangy.toggleRange(range);
                    dispatchStyleChange(range, this);
                }
            }
        } else {
            range = rangy.getSelection().getRangeAt(0);
            if (!_scope || checkScope(_scope, rangy.getSelection().getRangeAt(0))) {
                var className = this.className,
                    styles = this.style,
                    nodes, startNode, selection;

                if (this.rangy.isAppliedToSelection()) {
                    this.tagName.toLowerCase() === "a" ? document.execCommand("unlink") : classWrapper.rangy.undoToSelection();
                } else {
                    classWrapper.rangy.applyToSelection();

                    selection = rangy.getSelection();
                    range = selection.getRangeAt(0);
                    nodes = range.getNodes([1], function(node) {return node.className === className});

                    if (selection.isBackwards()) {
                        startNode = nodes[nodes.length - 1];

                        for (var style in styles) { // check for style consistency
                            if (startNode.style[style] !== styles[style]) {
                                startNode = nodes[nodes.length - 2];
                                break;
                            }
                        }

                        range.setStart(startNode.childNodes[0]);
                    } else {
                        startNode = nodes[0];

                        for (var style in styles) { // check for style consistency
                            if (startNode.style[style] !== styles[style]) {
                                startNode = nodes[1];
                                break;
                            }
                        }

                        range.setStart(startNode.childNodes[0]);
                    }

                    selection.setSingleRange(range);
                }

                dispatchStyleChange(range, this);
            }
        }
    }

    if (options) {
        if (typeof options === "string") {
            classWrapper.style.cssText = options
        } else {
            for (var key in options) {
                classWrapper[key] = options[key];
            }
        }
    }

    return classWrapper;
}



/**
 * Creates HrmlWrapper object with specified options
 * @param   {String}        tagName    Tag name that HtmlWrapper will use in  insert, wrap and edit methods when creating and editing HtmlElements.
 * @param   {Object/String} properties Either options object for ClassWrapper or src/href string (if tagName is "a" or "img").
 * @param   {String}        title      Title string (if tagName is either "a" or "img").
 * @returns {Object}        HtmlWrapper object with adjustable properties and insert/wrap/edit/clear methods.
 */
function createHtmlWrapper(tagName, properties, title) {
    if (typeof properties === "string") {
        if (tagName.toLowerCase() === "a") {
            properties = {href: properties};
        } else if (tagName.toLowerCase() === "img") {
            properties = {src: properties};
        }
        if (title) properties.title = title;
    }

    return new HtmlWrapper(tagName, properties);
}

function HtmlWrapper(tagName, properties) {
    this.tagName = tagName;
    this.properties = properties || {};
}

HtmlWrapper.prototype = {
    insert: function(properties, range) {
        var range = range || rangy.getSelection().getRangeAt(0),
            selection = rangy.getSelection().getRangeAt(0),
            newElement = document.createElement(this.tagName),
            newElements = [];

        if (properties) {
            if (typeof properties === "string") {
                if (this.tagName.toLowerCase() === "a") {
                    properties = {href: properties};
                } else if (this.tagName.toLowerCase() === "img") {
                    properties = {src: properties};
                }
            }
        }

        for (var prop in this.properties) {
            if (prop === "style") {
                for (var styleProp in this.properties.style) {
                    newElement.style[styleProp] = this.properties.style[styleProp];
                }
            } else {
                newElement[prop] = this.properties[prop];
            }
        }

        for (var newProp in properties) {
            if (prop === "style") {
                for (var newStyleProp in properties.style) {
                    newElement.style[newStyleProp] = properties.style[newStyleProp];
                }
            } else {
                newElement[newProp] = properties[newProp];
            }
        }

        if (Array.isArray(range)) {
            range.forEach(function(rn) {
                var clonedElement = newElement.cloneNode()

                rn.deleteContents();
                rn.insertNode(clonedElement);
                newElements.push(clonedElement);
            });
            return newElements;
        } else {
            range.deleteContents();
            range.insertNode(newElement);
            rangy.getSelection().setSingleRange(selection);
            return newElement;
        }
    },
    wrap: function(properties, range) {
        var range = range || rangy.getSelection().getRangeAt(0),
            newElement = document.createElement(this.tagName),
            newElements = [];

        if (properties) {
            if (typeof properties === "string") {
                if (this.tagName.toLowerCase() === "a") {
                    properties = {href: properties};
                }
            }
        }

        for (var prop in this.properties) {
            if (prop === "style") {
                for (var styleProp in this.properties.style) {
                    newElement.style[styleProp] = this.properties.style[styleProp];
                }
            } else {
                newElement[prop] = this.properties[prop];
            }
        }

        for (var newProp in properties) {
            if (prop === "style") {
                for (var newStyleProp in properties.style) {
                    newElement.style[newStyleProp] = properties.style[newStyleProp];
                }
            } else {
                newElement[newProp] = properties[newProp];
            }
        }

            if (Array.isArray(range)) {
                range.forEach(function(rn) {
                    var clonedElement = newElement.cloneNode()

                    rn.surroundContents(clonedElement);
                    newElements.push(clonedElement);
                });
                return newElements;
            } else {
                if (this.tagName.toLowerCase() === "a") {
                    document.execCommand("unlink");
                    range = rangy.getSelection().getRangeAt(0);
                }
                range.surroundContents(newElement);
                rangy.getSelection().setSingleRange(range);
                return newElement;
            }
    },
    edit: function(properties, range) {
        var range = range || rangy.getSelection().getRangeAt(0),
            node;

        if (Array.isArray(range)) {
            range.forEach(function(rn) {
                node = rn.startContainer;

                if (node.tagName.toLowerCase() === this.tagName.toLowerCase()) {
                    if (typeof properties === "string") {
                        if (this.tagName.toLowerCase() === "a") {
                            node.href = properties;
                        } else if (this.tagName.toLowerCase() === "img") {
                            node.src = properties;
                        }
                    } else {
                        for (var prop in properties) {
                            if (prop === "style") {
                                for (var styleProp in properties.style) {
                                    node.style[styleProp] = properties.style[styleProp];
                                }
                            } else {
                                node[prop] = properties[prop];
                            }
                        }
                    }
                }

            });
        } else {
            node = range.startContainer;

            if (node.tagName.toLowerCase() === this.tagName.toLowerCase()) {
                if (typeof properties === "string") {
                    if (this.tagName.toLowerCase() === "a") {
                        node.href = properties;
                    } else if (this.tagName.toLowerCase() === "img") {
                        node.src = properties;
                    }
                } else {
                    for (var prop in properties) {
                        if (prop === "style") {
                            for (var styleProp in properties.style) {
                                node.style[styleProp] = properties.style[styleProp];
                            }
                        } else {
                            node[prop] = properties[prop];
                        }
                    }
                }
            }
        }
    },
    clear: function() {
        if (this.tagName.toLowerCase() === "a") document.execCommand("unlink"); // Clear is supported only by anchors
    }
}

},{}]},{},[1]);
