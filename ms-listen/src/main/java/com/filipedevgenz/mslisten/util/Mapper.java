package com.filipedevgenz.mslisten.util;
import com.filipedevgenz.mslisten.dto.ResponseMessageTextDTO;
import com.filipedevgenz.mslisten.dto.RecivedMessageDTO;
import com.filipedevgenz.mslisten.model.Message;
import com.filipedevgenz.mslisten.service.RequestService;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class Mapper {

    static RequestService requestService;

    public static ResponseMessageTextDTO mapToResponse(Message message, String text) {
        var toResponseText = ResponseMessageTextDTO.fromText(text);
        return new ResponseMessageTextDTO("whatsapp"
                , message.getFrom(), "text",toResponseText);
    }

    public static Message mapToMessage(RecivedMessageDTO messageDTO) {
        RecivedMessageDTO.Message treatedMessage = messageDTO
                .entry().getFirst().changes().getFirst().value().messages().getFirst();
        if (treatedMessage.text().body() != null) {
            return new Message(treatedMessage.from(), treatedMessage.id()
                    , treatedMessage.timestamp(), treatedMessage.text().body());
        }
        requestService.sendMessageWithRetry("Formato Invalido", treatedMessage.from());
        return null;
    }
}
