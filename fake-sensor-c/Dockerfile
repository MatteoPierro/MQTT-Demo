FROM docker/whalesay:latest
RUN apt-get -y update && apt-get install -y git && apt-get install -y  build-essential
RUN git clone https://github.com/eclipse/paho.mqtt.c.git
RUN apt-get install -y libssl-dev
RUN cd paho.mqtt.c && make && sudo make install
COPY . /usr/src/mysensor
WORKDIR /usr/src/mysensor
RUN gcc -o fake-sensor-c fake-sensor-c.c -lpaho-mqtt3c