const http = require('http');
const express = require('express');
const app = express();
const bodyParser = require('body-parser');

  app.use(bodyParser.json());
//  app.get('/', (req, res) => res.sendFile(__dirname + '/dist/index.html'))
  app.use('/', express.static('dist'));

  var port = (process.env.PORT || process.argv[2] || 4200)

  const srv = app.listen(port, () => {
    console.log('Listening')
  })
