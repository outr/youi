var communicator = new Communicator();

function connectRealtime(pageId) {
    communicator.connect({
        createData: {
            pageId: pageId
        },
        on: {
            connected: function() {
                console.log('Connection established!');
            },
            error: function(err) {
                console.log('Error occurred: ' + err);
            },
            received: function(msg) {
                console.log('Received: ' + JSON.stringify(msg));
            }
        }
    });
}