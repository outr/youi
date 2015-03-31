WebFontConfig = {
    loaded: {},
    loading: function() {},
    active: function() {},
    inactive: function() {},
    fontloading: function(familyName, fvd) {},
    fontactive: function(familyName, fvd) {
        var key = familyName + ':' + fvd;
        if (WebFontConfig.loaded[key] == null) {            // Avoid duplicate calls
            WebFontConfig.loaded[key] = true;
            realtime.send({
                type: 'webFontActive',
                fontFamily: familyName,
                fontVariationDescription: fvd
            });
        }
    },
    fontinactive: function(familyName, fvd) {}
};