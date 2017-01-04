MQTT Demo
================

## Requirements

 * Java 1.7 and later
 * Maven 3.3.9 and later
 * Docker 1.12.5 and later
 * NodeJs 6 and later
 * Web Browser that supports websocket

## Run Broker

```bash
    $ docker run --rm --name vernemq -it -p 1883:1883 -p 9001:8080 erlio/docker-vernemq
```

## Create credentials store

```bash
    $ docker exec -it vernemq touch /etc/vernemq/vmq.passwd
```

## Create a new credential

```bash
    $ docker exec -it vernemq vmq-passwd -U /etc/vernemq/vmq.passwd <username>
    Password: <password>
    Reenter password: <password>
```

## Remove a credential

```bash
    $ docker exec -it vernemq vmq-passwd -D /etc/vernemq/vmq.passwd <username>
```

## Run Fake Sensor Java

Create a credential with username *sensor-java* and password *sensor-java*.


```bash
	$ cd fake-sensor-java   
	$ mvn compile
	$ mvn exec:java
```

## Run Fake Sensor C

Create a credential with username *sensor-c* and password *sensor-c*.

```bash
	$ cd fake-sensor-c   
	$ docker build -t fake-sensor-c .
	$ docker run --rm --name sensor-c -it --net=host fake-sensor-c ./fake-sensor-c
```

## Run Fake Sensor NodeJS

Create a credential with username *sensor-node* and password *sensor-node*.

```bash
	$ cd fake-sensor-nodejs
  	$ npm install
	$ npm start -- <username> <password>
```

## Run Logger Subscriber Java

Create a credential with username *logger-java* and password *logger-java*.

```bash
	$ cd logger-subscriber-java   
	$ mvn compile
	$ mvn exec:java
```

## Run Temperature Web Monitor

Create a credential with username *web-gui* and password *web-gui*.

Open `web-gui-websocket/index.html` within a web browser that supports websockets (eg Google Chrome).

## References and Kudos

 * https://hub.docker.com/r/erlio/docker-vernemq/
 * http://www.hivemq.com/blog/how-to-get-started-with-mqtt
 * https://eclipse.org/paho/clients/c/
 * https://www.eclipse.org/paho/files/mqttdoc/Cclient/_m_q_t_t_client_8h_source.html
 * http://jpmens.net/2014/07/03/the-mosquitto-mqtt-broker-gets-websockets-support/
