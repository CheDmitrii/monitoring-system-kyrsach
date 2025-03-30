package ru.system.monitoring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping // test controller
public class RealTimeDataController {

    @MessageMapping("/send")
    @SendTo("/topic/journal")
    public String sendMessage(String message, String id) {
        return id;
    }


//    public JournalEntityDTO monitoring()
}
