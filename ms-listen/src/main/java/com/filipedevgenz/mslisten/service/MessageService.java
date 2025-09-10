package com.filipedevgenz.mslisten.service;
import com.filipedevgenz.mslisten.dto.RecivedMessageDTO;
import com.filipedevgenz.mslisten.dto.ResponseMessageDocumentDTO;
import com.filipedevgenz.mslisten.model.Data;
import com.filipedevgenz.mslisten.model.Message;
import com.filipedevgenz.mslisten.util.ContextHolder;
import com.filipedevgenz.mslisten.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class MessageService {

    private final RequestService requestService;
    private final PasswordEncoder numberEncoder;
    final KafkaDataSendRequest kafkaDataSendRequest;

    @Async("ioBound")
    public void messageTreatment(RecivedMessageDTO recivedMessageDTO) {
        Message message = verifyMessage(recivedMessageDTO);
        if(message==null){
            return;
        }
        if(message.getText().equals("/info")){
            sendInfoToWhatsapp(message.getFrom());
            return;
        }

        String[] tokens = message.getText().split(" ");
        Data toSave;
        if(tokens.length == 3){
            toSave = new Data(tokens[0], tokens[1], tokens[2], message.getTimestamp(),message.getFrom());
        }else {
            toSave = new Data(tokens[0], tokens[1], message.getTimestamp(),message.getFrom());
        }
        kafkaDataSendRequest.SendRequest(toSave);
        requestService.sendMessageWithRetry
                ("dado adicionado, para informacoes de gastos, '/info; ",message.getFrom());
    }

    private Message verifyMessage(RecivedMessageDTO recivedMessageDTO){
            var message = Mapper.mapToMessage(recivedMessageDTO);
            if(message == null){
                return null;
            }
            return message;
    }

    protected void sendInfoToWhatsapp(String number) {
        var responseDTO = requestInfoToWhatsapp(numberEncoder.encode(number));
        if (responseDTO.getBody()==null){
            requestService.sendMessageWithRetry("nao ah dados", number);
        }
        requestService.sendDocumentWithRetry(number
                ,responseDTO.getBody().document().link()
                ,responseDTO.getBody().document().filename());
    }

    private ResponseEntity<ResponseMessageDocumentDTO> requestInfoToWhatsapp(String hashedNumber) {
        //TODO Request ms-info
        }
}
