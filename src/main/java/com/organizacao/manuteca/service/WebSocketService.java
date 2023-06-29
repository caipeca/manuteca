package com.organizacao.manuteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate MESSAGINGTEMPLATE;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingtemplate) {
        MESSAGINGTEMPLATE = messagingtemplate;
    }

    public void sendMessage(final  String topicSuffic){
        MESSAGINGTEMPLATE.convertAndSend("/tarefa/" + topicSuffic, "Default message from WS Service");
    }
}
