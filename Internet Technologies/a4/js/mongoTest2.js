document.getElementById("demo").innerHTML = "Test 3";
var MongoClient = require('mongodb').MongoClient;
document.getElementById("demo").innerHTML = "test 4";
// Retrieve
var MongoClient = require('mongodb').MongoClient;
var username="tt13fa";
var password = "5618293";
// Connect to the db
var client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username);
db=client[username];
db.products.find_one({'unit':product});
