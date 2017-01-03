package it.matteopierro;

import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class FakeSensor {

    public static final String TOPIC_TEMPERATURE = "home/room1/temperature";
    public static final String BROKER = "tcp://localhost:1883";
    public static final String CLIENT_ID = "Room1TemperatureSensor";

    public static void main(String[] args) throws Exception{

        MqttClient client = new MqttClient(BROKER, CLIENT_ID);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);

        System.out.println("Connecting to broker: "+BROKER);
        client.connect(connOpts);
        System.out.println("Connected");

        while (true) {    
            publishTemperature(client);
            Thread.sleep(1000);    
        }
    }

    private static void publishTemperature(MqttClient client) throws MqttException {

        final MqttTopic temperatureTopic = client.getTopic(TOPIC_TEMPERATURE);

        final int temperatureNumber = createRandomNumberBetween(20, 30);
        final String temperature = temperatureNumber + "°C";

        temperatureTopic.publish(new MqttMessage(temperature.getBytes()));

        System.out.println("Published data. Topic: " + temperatureTopic.getName() + "  Message: " + temperature);
    }

    public static int createRandomNumberBetween(int min, int max) {

        return new Random().nextInt(max - min + 1) + min;
    }
}
