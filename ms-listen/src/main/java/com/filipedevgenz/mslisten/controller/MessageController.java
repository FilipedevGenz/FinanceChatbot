package com.filipedevgenz.mslisten.controller;

import com.filipedevgenz.mslisten.dto.RecivedMessageDTO;
import com.filipedevgenz.mslisten.dto.MessageResponseDTO;
import com.filipedevgenz.mslisten.service.MessageService;
import com.filipedevgenz.mslisten.util.ContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    MessageService messageService;

    @Value("${token.security}")
    private String tokenSecurity;

    @PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody RecivedMessageDTO recivedMessageDTO) {
        var message = recivedMessageDTO.entry().get(0)
                .changes().get(0)
                .value()
                .messages().get(0);
        String number = message.from();
        ContextHolder.setNumero(number);
        if(message.text() == null){
            throw new NullPointerException();
        }
        messageService.messageTreatment(message.text().body(),message.timestamp());



        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.challenge") String challenge,
            @RequestParam(name = "hub.verify_token") String verifyToken) {
        if (tokenSecurity.equals(verifyToken) && "subscribe".equals(mode)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
    }
}
