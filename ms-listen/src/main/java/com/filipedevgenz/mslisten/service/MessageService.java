package com.filipedevgenz.mslisten.service;
import com.filipedevgenz.mslisten.dto.MessageResponseDTO;
import com.filipedevgenz.mslisten.model.Data;
import com.filipedevgenz.mslisten.util.ContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
//TODO implementar requisicao para o ms-crud

@Service
public class MessageService {
    @Value("${token.acess}")
    private String ACCESS_TOKEN;

    @Value("${routes.whatsappPostTestRoute}")
    private String WHATSAPP_API_URL;

    final RestClient restClient = RestClient.create();

    public Data messageTreatment(String text , String timestamp) {
        String[] values = text.split(" ");

        return new Data(values[0],values[1],timestamp);
    }

    //kafka para ms-crud
    private void saveData(Data data) {
        restClient.p
                //token seguranca
    }

    private ResponseEntity< MessageResponseDTO > sendMessage(String text, String uris) {
        MessageResponseDTO responseDTO = new MessageResponseDTO("Whatsapp"
                , ContextHolder.getNumero()
                ,"text"
                , MessageResponseDTO.fromText(text));

        ResponseEntity<String> request = restClient.post()
                .uri(WHATSAPP_API_URL)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDTO)
                .retrieve()
                .toEntity(String.class);

        return ResponseEntity.status(request.getStatusCode()).body(responseDTO);
    }
    }

    private ResponseEntity<Data> kafkaRequest(String token) {

    }

    private Double stringToDouble(String string) {
        return Double.parseDouble(string.replace(",","."));
    }
}
