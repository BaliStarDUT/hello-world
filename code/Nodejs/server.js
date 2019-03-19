var express = require("express");
var http = require("http");
var app = express();

// var httpServer = http.createServer(function(request,response){
//     response.writeHead(200,{'Content-Type':'text/json'});
//     response.end('{hello nodejs}');
// }).listen(8888);
// app.get('/',function(req,res){
//     res.send('Hello express');
// });

app.use("/",express.static('static'));
app.use("/node_modules",express.static('node_modules'));
app.use("/bower_components",express.static('bower_components'));

var httpServer = http.createServer(app).listen(8888,function(){
    var host = httpServer.address().address;
    var port = httpServer.address().port;
    console.log("Server running at http://%s:%s ...",host,port);
});
