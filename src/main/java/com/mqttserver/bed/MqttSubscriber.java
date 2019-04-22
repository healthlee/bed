package com.mqttserver.bed;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

/**
 * @author: benjamin health li
 * @create: 2019/4/22
 */
public class MqttSubscriber extends MqttPahoMessageDrivenChannelAdapter {

    public MqttSubscriber(String clientId, MqttPahoClientFactory clientFactory, String... topic) {
        super(clientId, clientFactory, topic);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {

        System.out.println(topic);
        System.out.println(new String(mqttMessage.getPayload()));
    }

}