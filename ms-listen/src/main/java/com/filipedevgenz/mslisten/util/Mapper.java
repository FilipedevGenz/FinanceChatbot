package com.filipedevgenz.mslisten.util;
import com.filipedevgenz.mslisten.dto.MessageResponseDTO;
import com.filipedevgenz.mslisten.model.Message;

public class Mapper {
    public static MessageResponseDTO mapToResponse(Message message,String text) {
        var toResponseText = MessageResponseDTO.fromText(text);
        return new MessageResponseDTO("whatsapp"
                , message.getFrom(), "text",toResponseText);
    }
}
