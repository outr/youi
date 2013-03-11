var visualSearchCache = {};

function setVisualSearchQuery(id, query) {
    var vs = window['vs' + id];
    vs.searchBox.value(query);
}

function createVisualSearch(id, url) {
    var facetCacheKey = id + '.facets';

    $(document).ready(function() {
        window['vs' + id] = VS.init({
            container: $('#' + id),
            query: '',
            autosearch: true,
            showFacets: true,
            unquotable: [],
            remainder: 'text',
            callbacks  : {
                search : function(query, searchCollection) {
                    visualSearchCache[facetCacheKey] = null;
                    jsFire($('#' + id), 'search', {
                        query: query,
                        facets: searchCollection.facets()
                    });
                },
                facetMatches : function(callback) {
                    var cache = visualSearchCache[facetCacheKey];
                    if (cache != null) {
                        callback(cache);
                    } else {
                        $.getJSON(url, {
                            requestType: 'facets',
                            r: Math.random()
                        }, function(result) {
//                            visualSearchCache[facetCacheKey] = result;
                            callback(result);
                        });
                    }
                },
                valueMatches : function(facet, searchTerm, callback) {
                    var facetResultsKey = id + '.' + facet;
                    var cache = visualSearchCache[facetResultsKey];
                    if (cache != null) {
                        callback(cache);
                    } else {
                        $.getJSON(url, {
                            requestType: 'values',
                            facet: facet,
                            term: searchTerm,
                            r: Math.random()
                        }, function(result) {
                            if (result.resultType == 'Complete') {
                                visualSearchCache[facetResultsKey] = result.results;
                            }
                            callback(result.results);
                        });
                    }
                }
            }
        });
    });
}