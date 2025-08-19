package com.filipedevgenz.mslisten.util;

import com.filipedevgenz.mslisten.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@ControllerAdvice
public class ExceptionHandller {
    @Value("${routes.whatsappPostTestRoute}")
    private String WHATSAPP_API_URL;

    @Value("${token.acess}")
    private String ACCESS_TOKEN;

    final RestClient restClient = RestClient.create();

    //ao enviar uma mensagem em qualquer formato nao texto, eh levantado NullPointer
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        MessageResponseDTO responseDTO = new MessageResponseDTO("Whatsapp"
                ,ContextHolder.getNumero()
                ,"text"
                , MessageResponseDTO.fromText("Formato Invalido, envie uma mensagem de texto"));

        restClient.post()
                .uri(WHATSAPP_API_URL)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDTO)
                .retrieve()
                .toEntity(String.class);
        return ResponseEntity.badRequest().body(responseDTO.toString());
    }
}
