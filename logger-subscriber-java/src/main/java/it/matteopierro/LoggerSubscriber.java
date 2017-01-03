package it.matteopierro;

import org.eclipse.paho.client.mqttv3.*;

public class LoggerSubscriber {

    public static final String TOPIC_HOME = "home/#";
    public static final String BROKER = "tcp://localhost:1883";
    public static final String CLIENT_ID = "LoggerSubscriber";

    public static void main(String[] args) throws Exception{

        MqttClient sampleClient = new MqttClient(BROKER, CLIENT_ID);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);

        System.out.println("Connecting to broker: "+BROKER);
        sampleClient.connect(connOpts);
        System.out.println("Connected");

        IMqttMessageListener messageListener = new SampleMessageListener();
        sampleClient.subscribe(TOPIC_HOME, messageListener);
    }

    private static class SampleMessageListener implements IMqttMessageListener {
        
        public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
            
            String message = new String(mqttMessage.getPayload());
            System.out.println("Message arrived. Topic: "+ topic +" Message: " + message);
        }
    }
}
