const mqtt = require('mqtt')

const [username, password] = process.argv.slice(2)
const client  = mqtt.connect('mqtt://localhost', {username, password})

client.on('connect', function () {
  console.log('-- connected')
  setInterval(function (client) {
    const temperature = Math.random() * 10
    client.publish('home/room2/temperature', `${temperature} °C`)
    console.log('-- temperature: %s °C', temperature)
  }, 2000, client)
})
