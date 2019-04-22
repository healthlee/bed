package com.mqttserver.bed.controller;

import com.mqttserver.bed.service.BedService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: benjamin health li
 * @create: 2019/4/22
 */
@RestController
public class MqttController {

    @Resource
    private BedService bedService;

    @RequestMapping("/send")
    public String sendMessage(String topic, String content) {

        bedService.send(topic, content);

        return "发送成功";
    }

}