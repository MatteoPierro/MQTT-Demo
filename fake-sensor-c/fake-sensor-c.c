#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include <unistd.h>
#include <MQTTClient.h>

#define ADDRESS     "tcp://localhost:1883"
#define CLIENTID    "Room1BrightnessSensor"
#define TOPIC       "home/room1/brightness"
#define TIMEOUT     10000L
#define SLEEP       1

int main(int argc, char* argv[])
{
    MQTTClient client;
    MQTTClient_connectOptions conn_opts = MQTTClient_connectOptions_initializer;
    int rc;

    MQTTClient_create(&client, ADDRESS, CLIENTID,
        MQTTCLIENT_PERSISTENCE_NONE, NULL);
    conn_opts.username = "sensor-c";
    conn_opts.password = "sensor-c";
    conn_opts.keepAliveInterval = 20;
    conn_opts.cleansession = 1;

    if ((rc = MQTTClient_connect(client, &conn_opts)) != MQTTCLIENT_SUCCESS)
    {
        printf("Failed to connect, return code %d\n", rc);
        exit(-1);
    }

    MQTTClient_deliveryToken token;
    
    while(1) {        
        char brightness[15];
        int brightness_value = rand() % (100 + 1);
        sprintf(brightness, "%d %%", brightness_value);
        
        MQTTClient_message pubmsg = MQTTClient_message_initializer;
        pubmsg.payload = brightness;
        pubmsg.payloadlen = strlen(brightness);
        
        MQTTClient_publishMessage(client, TOPIC, &pubmsg, &token);
        
        printf("Waiting for up to %d seconds for publication of %s\n"
                "on topic %s for client with ClientID: %s\n",
                (int)(TIMEOUT/1000), brightness, TOPIC, CLIENTID);
        
        rc = MQTTClient_waitForCompletion(client, token, TIMEOUT);
        
        if (rc == 0) {
            printf("Message delivered\n");     
        } else {
            printf("Message delivery failure\n");
        }
        
        sleep(SLEEP);
    }

    return 0;
}