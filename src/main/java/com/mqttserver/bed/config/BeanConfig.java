package com.mqttserver.bed.config;

import com.mqttserver.bed.MqttSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageHandler;

/**
 * @author: benjamin health li
 * @create: 2019/4/21
 */
@Slf4j
@Configuration
public class BeanConfig {
    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.cleanSession}")
    private Boolean cleanSession;

    @Value("${mqtt.async}")
    private Boolean async;

    @Value("${mqtt.defaultQos}")
    private Integer defaultQos;

    @Value("${mqtt.completionTimeout}")
    private Integer completionTimeout;

    @Value("${mqtt.keepAliveInterval}")
    private Integer keepAliveInterval;

    @Value("${mqtt.serverUri}")
    private String serverUri;

    @Value("${mqtt.topic}")
    private String topic;

    @Bean
    public MqttPahoClientFactory clientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { serverUri });
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setCleanSession(cleanSession);
        options.setKeepAliveInterval(keepAliveInterval);

        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     *发布
     * @return
     */
    @Bean
    public MessageHandler mqttHandler() {
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(clientId, clientFactory());

        handler.setAsync(async);
        handler.setDefaultQos(defaultQos);
        handler.setCompletionTimeout(completionTimeout);

        return handler;
    }


    /**
     * 订阅
     * @return
     */
    @Bean
    public MqttSubscriber mqttAdapter() {
        return new MqttSubscriber(clientId,clientFactory(),topic);
    }

}
