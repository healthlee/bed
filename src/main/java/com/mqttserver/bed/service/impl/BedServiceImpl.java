package com.mqttserver.bed.service.impl;

import com.mqttserver.bed.service.BedService;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: benjamin health li
 * @create: 2019/4/22
 */
@Service
public class BedServiceImpl implements BedService {

    @Resource
    private MqttPahoMessageHandler mqttHandler;

    @Override
    public void send(String topic, String content) {
        // 构建消息
        Message<String> messages = MessageBuilder.withPayload(content).setHeader(MqttHeaders.TOPIC, topic).build();
        // 发送消息
        mqttHandler.handleMessage(messages);
    }
}
