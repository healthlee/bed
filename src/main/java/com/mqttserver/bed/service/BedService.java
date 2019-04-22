package com.mqttserver.bed.service;

/**
 * @author: benjamin health li
 * @create: 2019/4/22
 */
public interface BedService {
    /**
     * 发送消息
     * @param topic 主题
     * @param content 内容
     */
    void send(String topic, String content);
}
