function createRichEditor(id) {
    CKEDITOR.replace(id);
    var lastValue;

    var editor = CKEDITOR.instances[id];
    editor.on('instanceReady', function() {
        editor.editable().on('blur', function() {
            var value = editor.getData();
            if (value != lastValue) {
                communicator.send('change', {
                    id: id,
                    value: value
                });
                communicator.send('event', {
                    id: id,
                    event: 'change'
                });
                lastValue = value;
            }
        });
    });
//    editor.on('blur', function() {
//        console.log('Blur!!!');
//        var value = editor.getData();
//        if (value != lastValue) {
//            communicator.send('change', {
//                id: id,
//                value: value
//            });
//            communicator.send('event', {
//                id: id,
//                event: 'change'
//            });
//            lastValue = value;
//        }
//    });
}

function updateRichEditor(id, value) {
    var editor = CKEDITOR.instances[id];
    editor.setData(value);
}