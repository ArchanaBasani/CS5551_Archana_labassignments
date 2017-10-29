var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mmongodb://root:root@ds125565.mlab.com:25565/asedemo';
MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
    console.log("Connected correctly to server.");
    db.close();
});

