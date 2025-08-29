package com.filipedevgenz.mslisten.util;
import com.filipedevgenz.mslisten.dto.ResponseMessageTextDTO;
import com.filipedevgenz.mslisten.dto.RecivedMessageDTO;
import com.filipedevgenz.mslisten.model.Message;

public class Mapper {
    public static ResponseMessageTextDTO mapToResponse(Message message, String text) {
        var toResponseText = ResponseMessageTextDTO.fromText(text);
        return new ResponseMessageTextDTO("whatsapp"
                , message.getFrom(), "text",toResponseText);
    }
    public static Message mapToMessage(RecivedMessageDTO messageDTO) {
        var message = messageDTO.entry().get(0)
                .changes().get(0)
                .value()
                .messages().get(0);
        return new Message(message.from(),message.id(),message.timestamp(),message.text().body());
    }
}
