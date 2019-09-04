package com.demo.controller;

import com.demo.utils.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chen
 * @date 2019/7/25 13:19
 */
@RestController
@RequestMapping("/send")
public class MsgController {

    @Autowired
    private MQSender mqSender;

    @RequestMapping("")
    public void send(@RequestParam("msg")String msg){
        mqSender.sendMsg(msg);
    }
}
