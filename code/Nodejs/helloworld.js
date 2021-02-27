console.log("Hello Node");
var http = require("http");
var events = require("events");
var url = require('url');
var util = require('util');

var eventEmitter = new events.EventEmitter();

var connectHandler = function connected(){
    console.log("connected-->");
    eventEmitter.emit("data_received");
}

eventEmitter.on("connection",connectHandler);
eventEmitter.on("data_received",function(){
    console.log("data_received-->");
})
setTimeout(function(){
    eventEmitter.emit("connection");
},1000);

console.log("event end");

http.createServer(function(request,response){
        eventEmitter.on("connection",connectHandler);
        response.writeHead(200,{'Content-Type': 'text/plain'});
        //response.write("Hello world");
        response.end(util.inspect(url.parse(request.url, true)));
        //response.end("<h1>Hello nodeJs</h1>");
    }
).listen(8888);
console.log("Start http server at 8888");
