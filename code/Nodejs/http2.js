const http2 = require('http2');
const fs = require('fs');

const server = http2.createSecureServer({
  key: fs.readFileSync('rsa.pk'),
  cert: fs.readFileSync('cert.pem')
});
// key: fs.readFileSync('agent2-key.pem'),
// cert: fs.readFileSync('agent2-cert.pem')
server.on('error', (err) => console.error(err));

server.on('stream', (stream, headers) => {
  // 流是一个双工流。
  stream.respond({
    'content-type': 'text/html; charset=utf-8',
    ':status': 200
  });
  stream.end('<h1>你好世界</h1>');
});

server.listen(8443);
